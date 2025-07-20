package com.example;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllDoneTasks() {
        Task probe = new Task();
        probe.setDone(true);
        Example<Task> example = Example.of(probe);

        return taskRepository.findAll(example);
    }

    public List<Task> getAllUnfinishedTasks() {
        Task probe = new Task();
        probe.setDone(false);
        Example<Task> example = Example.of(probe);

        return taskRepository.findAll(example);
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }
}
