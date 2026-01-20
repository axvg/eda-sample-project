package com.group.lms.enrollments.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.group.lms.enrollments.application.command.EnrollmentCommandHandler;
import com.group.lms.shared.infrastructure.eventsourcing.MemoryEventStore;

@Configuration
public class EnrollmentConfiguration {
    @Bean
    public EnrollmentCommandHandler enrollmentCommandHandler(MemoryEventStore eventStore) {
        return new EnrollmentCommandHandler(eventStore);
    }
}
