package com.group.lms.enrollments.application.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnrollmentReadModel {
    private final String enrollmentId;
    private final String studentId;
    private final String courseId;

    // desnormalized data
    private final String studentName;

    private int progressPercentage;

    public EnrollmentReadModel(String enrollmentId) {
        this.enrollmentId = enrollmentId;
        this.studentId = null;
        this.courseId = null;
        this.studentName = null;
        this.progressPercentage = 0;
    }
}
