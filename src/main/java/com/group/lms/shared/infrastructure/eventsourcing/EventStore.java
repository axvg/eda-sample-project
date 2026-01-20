package com.group.lms.shared.infrastructure.eventsourcing;

import java.util.List;

import com.group.lms.shared.domain.event.DomainEvent;

public interface EventStore {
    void save(String aggregateId, DomainEvent event);

    List<DomainEvent> getEvents(String aggregateId);
}
