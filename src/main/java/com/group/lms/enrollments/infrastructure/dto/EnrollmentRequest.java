package com.group.lms.enrollments.infrastructure.dto;

import lombok.Data;

@Data
public class EnrollmentRequest {
    private String studentId;
    private String studentName;
    private String courseId;
}
