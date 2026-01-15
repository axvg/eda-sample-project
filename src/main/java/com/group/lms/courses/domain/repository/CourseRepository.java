package com.group.lms.courses.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.lms.courses.domain.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {   
}