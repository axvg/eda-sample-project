package com.group.lms.payment.application.eventhandler;

import java.util.Random;

import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.group.lms.courses.domain.event.CoursePublishedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PaymentHandler {
    private final Random random = new Random();

    @Async("eventExecutor") // no genera bloqueos
    @EventListener
    @Retryable(
        maxAttempts = 2,
        backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public void handleCoursePublished(CoursePublishedEvent event) throws InterruptedException {
        log.info("[{}] Processing payment...", Thread.currentThread().getName());
 
        if (random.nextBoolean()) {
            log.warn("Payment processing taking longer than expected for course id: {}", event.getCourseId());
            throw new RuntimeException("Payment processing failed due to timeout");
        }
        log.info("Payment finished for course: {}", event.getTitle());
    }


    @Recover
    public void recover(RuntimeException e, CoursePublishedEvent event) {
        log.error("All retry attempts exhausted for course id: {}", event.getCourseId());
        // Store in DLQ or take alternative action
        // TODO: Implement DLQ storage logic
    }
}
