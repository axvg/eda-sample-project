package com.group.lms.courses.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA
@Entity
@Data
@Builder
// Lombok
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String instructor;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    private LocalDateTime createdAt;


    public enum CourseStatus {
        DRAFT,
        PUBLISHED,
        ARCHIVED
    }

}
