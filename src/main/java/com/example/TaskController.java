package com.example;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:5500")
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
    public void addNewTask(@RequestBody @Valid Task task) {
        taskService.addTask(task);
    }

    @PostMapping("/batch")
    public void addMultipleTasks(@RequestBody @Valid List<@Valid Task> tasks) {
        taskService.addMoreTasks(tasks);
    }

    @PutMapping("/{taskName}/{status}")
    public void taskFinished(@PathVariable String taskName, @PathVariable Boolean status) {
        taskService.setTaskStatus(taskName, status);
    }

    @DeleteMapping("/{taskName}")
    public void deleteTaskById(@PathVariable String taskName) {
        taskService.deleteTask(taskName);
    }

    @DeleteMapping("/finished")
    public void deleteAllFinishedTasks() {
        taskService.deleteFinishedTasks();
    }
}
