package com.group.lms.courses.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.lms.courses.application.CourseUseCase;
import com.group.lms.courses.domain.model.Course;
import com.group.lms.courses.infrastructure.web.dto.CreateCourseRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseUseCase courseUseCase;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CreateCourseRequest request) {
        Course course = courseUseCase.createCourse(
            request.getTitle(),
            request.getDescription(),
            request.getInstructor()
        );
        return ResponseEntity.ok(course);
    }    
}
