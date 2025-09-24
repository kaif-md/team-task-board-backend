package com.example.teamtaskboardbackend.controller;

import com.example.teamtaskboardbackend.dto.TaskRequest;
import com.example.teamtaskboardbackend.entity.Task;
import com.example.teamtaskboardbackend.entity.User;
import com.example.teamtaskboardbackend.repository.UserRepository;
import com.example.teamtaskboardbackend.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepo;

    public TaskController(TaskService taskService, UserRepository userRepo) {
        this.taskService = taskService;
        this.userRepo = userRepo;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskRequest req) {
        User assignee = userRepo.findById(req.getAssigneeId()).orElse(null);

        Task task = Task.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .priority(Task.Priority.valueOf(req.getPriority().toUpperCase()))
                .assignee(assignee)
                .status(Task.Status.valueOf(req.getStatus().toUpperCase()))
                .dueDate(req.getDueDate())
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();

        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskRequest req) {
        Task task = taskService.getById(id);
        if (task == null) return null;

        User assignee = userRepo.findById(req.getAssigneeId()).orElse(null);
        task.setTitle(req.getTitle());
        task.setDescription(req.getDescription());
        task.setPriority(Task.Priority.valueOf(req.getPriority().toUpperCase()));
        task.setAssignee(assignee);
        task.setStatus(Task.Status.valueOf(req.getStatus().toUpperCase()));
        task.setDueDate(req.getDueDate());
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted";
    }
}
