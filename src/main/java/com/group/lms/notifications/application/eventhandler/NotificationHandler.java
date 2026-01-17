package com.group.lms.notifications.application.eventhandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.group.lms.courses.domain.event.CoursePublishedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationHandler {
    @Async("eventExecutor")
    @EventListener
    public void handleCoursePublished(CoursePublishedEvent event) throws InterruptedException {
        log.info("{} Sending notifications...", Thread.currentThread().getName());
        Thread.sleep(4000); // simulate delay
        log.info("email sent for course: {}", event.getTitle());

    }
}
