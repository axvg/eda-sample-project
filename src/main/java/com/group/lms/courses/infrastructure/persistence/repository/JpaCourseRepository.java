package com.group.lms.courses.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.lms.courses.domain.model.Course;

public interface JpaCourseRepository extends JpaRepository<Course, Long> {
}
