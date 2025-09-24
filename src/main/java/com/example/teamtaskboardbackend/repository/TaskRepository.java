package com.example.teamtaskboardbackend.repository;

import com.example.teamtaskboardbackend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignee_Id(Long assigneeId);
    List<Task> findByPriority(Task.Priority priority);
}
