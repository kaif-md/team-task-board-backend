package com.example.teamtaskboardbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dueDate;
    private LocalDateTime created;
    private LocalDateTime updated;

    public enum Priority { LOW, MEDIUM, HIGH }
    public enum Status { BACKLOG, IN_PROGRESS, REVIEW, DONE }
}
