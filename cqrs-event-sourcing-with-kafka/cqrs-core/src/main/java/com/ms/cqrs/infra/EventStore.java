package com.ms.cqrs.infra;

import com.ms.cqrs.event.BaseEvent;

import java.util.List;
public interface EventStore {
    void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvent(String aggregateId);
}
