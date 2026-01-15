package com.group.lms.courses.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.group.lms.courses.domain.model.Course;
import com.group.lms.courses.domain.repository.CourseRepository;
import com.group.lms.courses.infrastructure.persistence.repository.JpaCourseRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourseRepositoryAdapter implements CourseRepository{
    private final JpaCourseRepository jpaCourseRepository;

    @Override
    public Course save(Course course) {
        return jpaCourseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return jpaCourseRepository.findById(id);
    }
}