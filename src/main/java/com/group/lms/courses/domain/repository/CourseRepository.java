package com.group.lms.courses.domain.repository;

import com.group.lms.courses.domain.model.Course;

public interface CourseRepository {
    public Course save(Course course);
}