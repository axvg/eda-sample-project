package com.group.lms.courses.application;

import org.springframework.stereotype.Service;

import com.group.lms.courses.domain.event.CourseCreatedEvent;
import com.group.lms.courses.domain.model.Course;
import com.group.lms.courses.domain.repository.CourseRepository;
import com.group.lms.shared.domain.event.EventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseUseCase {
    private final CourseRepository repository;
    private final EventPublisher eventPublisher;

    public Course createCourse(String title, String description, String instructor) {
        Course course = Course.builder()
            .title(title)
            .description(description)
            .instructor(instructor)
            .status(Course.CourseStatus.DRAFT)
            .build();

        Course saved = repository.save(course);
        log.info("Course created: {} - {} - {}", saved.getId(), saved.getTitle(), saved.getInstructor());

        eventPublisher.publish(
            new CourseCreatedEvent(saved.getId().toString(), saved.getTitle(), saved.getInstructor())
        );

        return saved;
    }

}
