package com.group.lms.shared.domain.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher {
    private final ApplicationEventPublisher publisher;

    public void publish(DomainEvent event) {
        log.info("Publishing event: {} - {} - {}",
            event.getEventId(),
            event.getEventType(),
            event.getOcurredOn()
        );
        publisher.publishEvent(event);
    }
}
