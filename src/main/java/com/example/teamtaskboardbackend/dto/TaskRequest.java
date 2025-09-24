package com.example.teamtaskboardbackend.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TaskRequest {
    private String title;
    private String description;
    private String priority;
    private Long assigneeId;
    private String status;
    private LocalDateTime dueDate;
}