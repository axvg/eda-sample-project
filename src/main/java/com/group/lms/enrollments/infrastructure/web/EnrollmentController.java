package com.group.lms.enrollments.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.lms.enrollments.application.command.EnrollStudentCommand;
import com.group.lms.enrollments.application.command.EnrollmentCommandHandler;
import com.group.lms.enrollments.application.query.EnrollmentQueryRepository;
import com.group.lms.enrollments.application.query.EnrollmentReadModel;
import com.group.lms.enrollments.domain.model.Enrollment;
import com.group.lms.enrollments.infrastructure.dto.EnrollmentRequest;
import com.group.lms.enrollments.infrastructure.dto.EnrollmentResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentCommandHandler enrollmentCommandHandler;
    private final EnrollmentQueryRepository enrollmentQueryRepository;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> enrollStudent(
        @RequestBody EnrollmentRequest request
    ) {
        EnrollStudentCommand command = new EnrollStudentCommand(
            request.getStudentId(),
            request.getStudentName(),
            request.getCourseId()
        );
        String enrollmentId = enrollmentCommandHandler.enrollStudent(command);
        EnrollmentResponse response = new EnrollmentResponse(enrollmentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{enrollmentId}/lessons/{lessonId}")
    public ResponseEntity<Void> addLesson(
        @PathVariable String enrollmentId,
        @PathVariable String lessonId
    ) {
        enrollmentCommandHandler.addLesson(enrollmentId, lessonId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{enrollmentId}/progress")
    public ResponseEntity<EnrollmentResponse> getEnrollmentProgress(
        @PathVariable String enrollmentId
    ) {
        Enrollment enrollment = enrollmentCommandHandler.getEnrollmentProgress(enrollmentId);

        log.info("Enrollment {} - Current progress: {}%", enrollmentId, enrollment.getProgressPercentage());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentReadModel> getEnrollmentDetails(
        @PathVariable String enrollmentId
    ) {
        EnrollmentReadModel readModel = enrollmentQueryRepository.findById(enrollmentId)
            .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        return ResponseEntity.ok(readModel);
    }
}
