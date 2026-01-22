package com.group.lms.courses.application;

import static com.group.lms.shared.infrastructure.config.RabbitMQConfig.COURSE_PUBLISHED_RK;

import com.group.lms.courses.domain.event.CoursePublishedEvent;
import com.group.lms.courses.domain.model.Course;
import com.group.lms.courses.domain.repository.CourseRepository;
import com.group.lms.shared.domain.event.EventPublisher;
import com.group.lms.shared.domain.event.RabbitMQEventPublisher;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class PublishCourseUseCase {
    private final CourseRepository repository;
    // private final EventPublisher eventPublisher;
    private final RabbitMQEventPublisher eventPublisher;

    @Transactional // use transaction because of multiple operations 
    public Course publishCourse(Long courseId, double price) {
        Course course = repository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        course.setStatus(Course.CourseStatus.PUBLISHED);
        Course saved = repository.save(course);

        log.info("Course published: {}", saved.getId());


        eventPublisher.publish(
            COURSE_PUBLISHED_RK,
            new CoursePublishedEvent(
                saved.getId().toString(),
                saved.getTitle(),
                price
            )
        );

        return saved;
    }
}
