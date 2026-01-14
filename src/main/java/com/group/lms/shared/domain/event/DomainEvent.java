package com.group.lms.shared.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;



@Getter
public class DomainEvent {
    private final String eventId;
    private final String eventType;
    private final LocalDateTime ocurredOn;


    public DomainEvent() {
        // uuid is in format 8-4-4-4-12
        this.eventId = UUID.randomUUID().toString();
        // getClass() returns the runtime class of this object
        // example : UserCreatedEvent
        // getSimpleName() returns the simple name of the underlying class
        // example : 'UserCreatedEvent'
        this.eventType = this.getClass().getSimpleName();
        this.ocurredOn = LocalDateTime.now();
    }
    
}
