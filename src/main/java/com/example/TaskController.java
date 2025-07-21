package com.example;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/finished")
    public List<Task> getDoneTasks() {
        return taskService.getAllDoneTasks();
    }

    @GetMapping("/unfinished")
    public List<Task> getUnfinishedTasks() {
        return taskService.getAllUnfinishedTasks();
    }

    @PostMapping
    public void addNewTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @PostMapping("/batch")
    public void addMultipleTasks(@RequestBody List<Task> tasks) {
        taskService.addMoreTasks(tasks);
    }

    @PutMapping("/{id}")
    public void taskFinished(@PathVariable Integer id) {
        taskService.makeTaskFinished(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }

    @DeleteMapping("/finished")
    public void deleteAllFinishedTasks() {
        taskService.deleteFinishedTasks();
    }
}
