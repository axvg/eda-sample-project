package com.group.lms.enrollments.application.projection;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.group.lms.enrollments.application.query.EnrollmentQueryRepository;
import com.group.lms.enrollments.application.query.EnrollmentReadModel;
import com.group.lms.enrollments.domain.event.LessonCompletedEvent;
import com.group.lms.enrollments.domain.event.StudentEnrolledEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class EnrollmentProjection {
    private final EnrollmentQueryRepository repository;

    @EventListener
    public void onStudentEnrolled(StudentEnrolledEvent event){
        log.info("Processing StudentEnrolledEvent for student: {}", event.getStudentName());

        var readModel = new EnrollmentReadModel(
            event.getEnrollmentId(),
            event.getStudentId(),
            event.getCourseId(),
            event.getStudentName(),
            0
        );
        repository.save(readModel);
    }

    @EventListener
    public void onLessonCompleted(LessonCompletedEvent event){
        log.info("Processing LessonCompletedEvent for enrollment: {}", event.getEnrollmentId());

        EnrollmentReadModel readModel = repository.findById(event.getEnrollmentId())
            .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        readModel.setProgressPercentage(readModel.getProgressPercentage() +  event.getNewProgressPercentage());

        repository.save(readModel); 
    }
}
