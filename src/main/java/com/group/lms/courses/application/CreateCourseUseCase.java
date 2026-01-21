package com.group.lms.courses.application;

import static com.group.lms.shared.infrastructure.config.RabbitMQConfig.COURSE_CREATED_RK;

import java.time.LocalDateTime;

import com.group.lms.courses.domain.event.CourseCreatedEvent;
import com.group.lms.courses.domain.model.Course;
import com.group.lms.courses.domain.repository.CourseRepository;
import com.group.lms.shared.domain.event.EventPublisher;
import com.group.lms.shared.domain.event.RabbitMQEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateCourseUseCase {
    private final CourseRepository repository;
    // private final EventPublisher eventPublisher;
    private final RabbitMQEventPublisher eventPublisher;

    public Course createCourse(String title, String description, String instructor) {
        Course course = Course.builder()
            .title(title)
            .description(description)
            .instructor(instructor)
            .status(Course.CourseStatus.DRAFT)
            .createdAt(LocalDateTime.now())
            .build();

        Course saved = repository.save(course);
        log.info("Course created: {} - {} - {}", saved.getId(), saved.getTitle(), saved.getInstructor());

        eventPublisher.publish(
            COURSE_CREATED_RK,
            new CourseCreatedEvent(saved.getId().toString(), saved.getTitle(), saved.getInstructor())
        );

        return saved;
    }

}
