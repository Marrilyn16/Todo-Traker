package com.example.week8task.entity;

import com.example.week8task.dto.TaskDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String status;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime completedAt;

    private Long user_id;


    public Task(TaskDTO taskDTO) {

        this.title = taskDTO.getTitle();
        this.description = taskDTO.getDescription();
        this.status = taskDTO.getStatus();
    }


}
