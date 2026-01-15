package com.group.lms.notifications;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.group.lms.courses.domain.event.CourseCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CourseEventHandler {
    @EventListener
    public void handleCourseCreated(CourseCreatedEvent event) {
        log.info("Handling course created: {} - {} - {}",
            event.getCourseId(),
            event.getTitle(),
            event.getInstructor()
        );
        // TODO: Send notifications
   
        sendEmailNotification(event);
    }

    private void sendEmailNotification(CourseCreatedEvent event) {
        log.info("Sending notification email for created course: {} - {}",
            event.getCourseId(),
            event.getTitle()
        );
    }

}
