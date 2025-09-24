package com.example.teamtaskboardbackend.repository;

import com.example.teamtaskboardbackend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask_Id(Long taskId);
}
