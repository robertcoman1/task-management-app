package com.example;
import java.util.List;
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

    public void addTask(Task task) {
        taskRepository.save(task);
    }
}
