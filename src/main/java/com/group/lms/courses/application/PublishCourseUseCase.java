package com.group.lms.courses.application;

import org.springframework.stereotype.Service;

import com.group.lms.courses.domain.event.CoursePublishedEvent;
import com.group.lms.courses.domain.model.Course;
import com.group.lms.courses.domain.repository.CourseRepository;
import com.group.lms.shared.domain.event.EventPublisher;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class PublishCourseUseCase {
    private final CourseRepository repository;
    private final EventPublisher eventPublisher;

    @Transactional // use transaction because of multiple operations 
    public Course publishCourse(Long courseId, double price) {
        Course course = repository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        course.setStatus(Course.CourseStatus.PUBLISHED);
        Course saved = repository.save(course);

        log.info("Course published: {}", saved.getId());


        eventPublisher.publish(
            new CoursePublishedEvent(
                saved.getId().toString(),
                saved.getTitle(),
                price
            )
        );

        return saved;
    }
}
