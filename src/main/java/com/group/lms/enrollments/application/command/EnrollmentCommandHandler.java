package com.group.lms.enrollments.application.command;

import com.group.lms.enrollments.domain.event.LessonCompletedEvent;
import com.group.lms.enrollments.domain.event.StudentEnrolledEvent;
import com.group.lms.enrollments.domain.model.Enrollment;
import com.group.lms.shared.infrastructure.eventsourcing.MemoryEventStore;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EnrollmentCommandHandler {
    private final MemoryEventStore eventStore;

    public String enrollStudent(EnrollStudentCommand command) {
        // generate enrollment ID
        String enrollmentId = "enrollment-" + System.currentTimeMillis();

        // create enrollment event
        StudentEnrolledEvent event = new StudentEnrolledEvent(
            enrollmentId,
            command.getStudentId(),
            command.getStudentName(),
            command.getCourseId()
        );

        // store event
        eventStore.save(enrollmentId, event);

        return enrollmentId;
    }

    public void addLesson(String enrollmentId, String lessonId) {
        var events = eventStore.getEvents(enrollmentId);
        // rebuild enrollment state
        var enrollment = Enrollment.fromEvents(events);
        // calculate new progress
        int newProgress = enrollment.getProgressPercentage() + 10;

        log.info("Enrollment {} - New progress after completing lesson {}: {}%", enrollmentId, lessonId, newProgress);

        // create lesson completed event
        var lessonCompletedEvent = new LessonCompletedEvent(
            enrollmentId,
            lessonId,
            newProgress);

        // store event
        eventStore.save(enrollmentId, lessonCompletedEvent);
    }

    public Enrollment getEnrollmentProgress(String enrollmentId) {
        // get all events for the enrollment ID
        var events = eventStore.getEvents(enrollmentId);
        // rebuild and return enrollment state
        var enrollment = Enrollment.fromEvents(events);
        return enrollment;
    }
}
