package com.group.lms.enrollments.domain.model;

import java.util.List;

import com.group.lms.shared.domain.event.DomainEvent;
import com.group.lms.enrollments.domain.event.LessonCompletedEvent;
import com.group.lms.enrollments.domain.event.StudentEnrolledEvent;

import lombok.Getter;

@Getter
public class Enrollment {
    private String enrollmentId;
    private String studentId;
    private String studentName;
    private String courseId;
    private int progressPercentage;

    public Enrollment() {
        this.progressPercentage = 0;
    }

    public static Enrollment fromEvents (List<DomainEvent> events) {
        Enrollment enrollment = new Enrollment();
        for (DomainEvent event : events) {
            enrollment.apply(event);
        }
        return enrollment;
    }

    private void apply(DomainEvent event) {
        if (event instanceof StudentEnrolledEvent e) {
            this.enrollmentId = e.getEnrollmentId();
            this.studentId = e.getStudentId();
            this.studentName = e.getStudentName();
            this.courseId = e.getCourseId();
        } else if (event instanceof LessonCompletedEvent e) {
            this.progressPercentage = e.getNewProgressPercentage();
        }
    }
}
