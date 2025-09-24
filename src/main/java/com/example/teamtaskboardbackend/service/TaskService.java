package com.example.teamtaskboardbackend.service;

import com.example.teamtaskboardbackend.entity.Task;
import com.example.teamtaskboardbackend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task) {
        task.setCreated(LocalDateTime.now());
        task.setUpdated(LocalDateTime.now());
        return taskRepo.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    public Task updateTask(Task task) {
        task.setUpdated(LocalDateTime.now());
        return taskRepo.save(task);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
