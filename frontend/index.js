function getStatus(done) {
    return done ? "finished" : "unfinished";
}

function isFinished(status) {
    return status === "finished";
}

function getTaskName(button_name) {
    left = 0;
    right = button_name.length - 1;

    left = button_name.indexOf(".");
    if (left === -1) {
        return "";
    }
    right = button_name.lastIndexOf("->");

    return button_name.slice(left + 1, right);
}

function displayData(data) {
    const task_cont = document.getElementById("tasksContainer");
    task_cont.innerHTML = "";
    index = 1;
    data.forEach(task => {
        const b = document.createElement("button");
        b.className = "tasksContainerButton";
        b.textContent = index + "." + task.taskName + "->" + getStatus(task.done);
        task_cont.appendChild(b);
        index += 1;
    });
}

async function getTasks() {
    const url = "http://localhost:8080/api/v1/tasks";
    try {
        const response = await fetch(url)
        if (!response.ok) {
            throw new Error("Response status: " + response.status);
        }

        const data = await response.json();
        displayData(data);
    } catch (error) {
        console.error(error.message);
    }
}

async function getFinishedTasks() {
    const url = "http://localhost:8080/api/v1/tasks/finished"
    try {
        const response = await fetch(url)
        if (!response.ok) {
            throw new Error("Response status: " + response.status)
        }
        const data = await response.json();
        displayData(data);
    } catch (error) {
        console.error(error.message)
    }
}

async function getUnfinishedTasks() {
    const url = "http://localhost:8080/api/v1/tasks/unfinished"
    try {
        const response = await fetch(url)
        if (!response.ok) {
            throw new Error("Response status: " + response.status)
        }
        const data = await response.json();
        displayData(data);
    } catch (error) {
        console.error(error.message)
    }
}

async function addTask() {
    let task_name = window.prompt("Enter task name:");
    const url = "http://localhost:8080/api/v1/tasks";
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({"taskName": task_name, "done": false})
        });
        if (!response.ok) {
            throw new Error("Response status: " + response.status);
        }
        getTasks();
    } catch (error) {
        console.error(error.message);
    }
}

async function removeTask() {
    let task_name = window.prompt("Enter task name:");
    const url = "http://localhost:8080/api/v1/tasks/" + task_name;
    try {
        const response = await fetch(url, {
            method: "DELETE"
        });
        if (!response.ok) {
            throw new Error("Response status: " + response.status);
        }
        getTasks();
    } catch (err) {
        console.error(err.message);
    }
}

async function removeAllFinished() {
    const url = "http://localhost:8080/api/v1/tasks/allFinished"
    try {
        const response = await fetch(url, {
            method: "DELETE"
        });
        if (!response.ok) {
            throw new Error("Response status: " + response.status);
        }
        getTasks();
    } catch (err) {
        console.error(err.message)
    }
}

async function setTaskStatus(task_name) {
    let yesOrNo = window.prompt("Modify task status? (finished or unfinished)").trim().toLowerCase();

    while (yesOrNo !== "finished" && yesOrNo !== "unfinished") {
        yesOrNo = window.prompt("Write finished or unfinished to set the task status!!").trim().toLowerCase();
    }

    const url = `http://localhost:8080/api/v1/tasks/${task_name}/${isFinished(yesOrNo)}`;
    const response = await fetch(url, {
        method: "PUT"
    });
    getTasks();
}

document.getElementById("showTasks").onclick = getTasks;
document.getElementById("addButton").onclick = addTask;
document.getElementById("removeTask").onclick = removeTask;
document.getElementById("removeFinishedTasks").onclick = removeAllFinished;
document.getElementById("showFinishedTasks").onclick = getFinishedTasks;
document.getElementById("showUnfinishedTasks").onclick = getUnfinishedTasks;
document.getElementById("tasksContainer").addEventListener("click", function (event) {
    const clickedButton = event.target;
    button_name = getTaskName(clickedButton.textContent)
    if (button_name != "") {
        setTaskStatus(button_name);
    }
})
