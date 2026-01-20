package com.group.lms.shared.infrastructure.dlq;

import com.group.lms.shared.domain.event.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FailedEvent {
    private final DomainEvent event;
    private final String reason;
    private final long timestamp;
}