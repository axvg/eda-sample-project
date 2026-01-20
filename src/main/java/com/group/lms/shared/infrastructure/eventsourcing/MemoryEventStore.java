package com.group.lms.shared.infrastructure.eventsourcing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.group.lms.shared.domain.event.DomainEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemoryEventStore implements EventStore {
    private final Map<String, List<DomainEvent>> stores = new ConcurrentHashMap<>();
    private final ApplicationEventPublisher publisher;

    public MemoryEventStore(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    @Override
    public void save(String aggregateId, DomainEvent event) {
        // add
        stores.computeIfAbsent(aggregateId, k -> new ArrayList<>()).add(event);
        // publish
        publisher.publishEvent(event);
    }

    @Override
    public List<DomainEvent> getEvents(String aggregateId) {
        return new ArrayList<>(stores.getOrDefault(aggregateId, new ArrayList<>()));
    }
}
