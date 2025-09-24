package com.example.teamtaskboardbackend.service;

import com.example.teamtaskboardbackend.entity.Comment;
import com.example.teamtaskboardbackend.repository.CommentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepo;

    public CommentService(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment addComment(Comment comment) {
        comment.setCreated(LocalDateTime.now());
        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsByTask(Long taskId) {
        return commentRepo.findByTask_Id(taskId);
    }
}