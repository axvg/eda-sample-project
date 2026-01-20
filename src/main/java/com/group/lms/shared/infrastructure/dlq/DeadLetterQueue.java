package com.group.lms.shared.infrastructure.dlq;

import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.stereotype.Component;

import com.group.lms.shared.domain.event.DomainEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DeadLetterQueue {
    private final ConcurrentLinkedDeque<FailedEvent> failedEvents = new ConcurrentLinkedDeque<>();

    public void add(DomainEvent event, Exception exception) {
        FailedEvent failedEvent = new FailedEvent(
            event,
            exception.getMessage(),
            System.currentTimeMillis()
        );
        failedEvents.add(failedEvent);
        log.error("Event added to DLQ: {} [{}]", event.getEventType(), event.getEventId());
    }
}
