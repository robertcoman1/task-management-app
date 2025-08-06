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
        List<Task> list_tasks = taskRepository.findAll();
        for (Task t : list_tasks) {
            if (t.equals(task)) {
                return;
            } else if (t.normString().equals(task.normString())) {
                t.setDone(task.getDone());
                taskRepository.save(t);
                return;
            }
        }

        taskRepository.save(task);
    }

    public void addMoreTasks(List<Task> tasks) {
        for (Task t : tasks) {
            addTask(t);
        }
    }

    public void deleteTask(String taskName) {
        String normatedTaskName = taskName.trim().replaceAll("\\s+", " ").toLowerCase();

        taskRepository.findAll().forEach((task) -> {
            if (task.normString().equals(normatedTaskName)) {
                taskRepository.delete(task);
            }
        });
    }

    public void deleteFinishedTasks() {
        taskRepository.deleteAll(getAllDoneTasks());
    }

    public void makeTaskFinished(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDone(true);
        taskRepository.save(task);
    }
}
