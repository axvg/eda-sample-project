package com.group.lms.courses.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.group.lms.courses.application.CreateCourseUseCase;
import com.group.lms.courses.application.PublishCourseUseCase;
import com.group.lms.courses.domain.repository.CourseRepository;
import com.group.lms.shared.domain.event.EventPublisher;

@Configuration
public class BeanConfiguration {
    @Bean
    public CreateCourseUseCase createCourseUseCase(
        CourseRepository repository,
        EventPublisher eventPublisher) {
        return new CreateCourseUseCase(repository, eventPublisher);
    }

    @Bean
    public PublishCourseUseCase publishCourseUseCase(
        CourseRepository repository,
        EventPublisher eventPublisher) {
        return new PublishCourseUseCase(repository, eventPublisher);
    }

}
