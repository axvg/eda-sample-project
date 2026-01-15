package com.group.lms.courses.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.lms.courses.application.CreateCourseUseCase;
import com.group.lms.courses.application.PublishCourseUseCase;
import com.group.lms.courses.domain.model.Course;
import com.group.lms.courses.infrastructure.web.dto.CreateCourseRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CreateCourseUseCase createCourseUseCase;
    private final PublishCourseUseCase publishCourseUseCase;


    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CreateCourseRequest request) {
        Course course = createCourseUseCase.createCourse(
            request.getTitle(),
            request.getDescription(),
            request.getInstructor()
        );
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<Course> publishCourse(
        @PathVariable Long id,
        @RequestParam double price) {
            Course course = publishCourseUseCase.publishCourse(id, price);
            return ResponseEntity.ok(course);
        }
}
