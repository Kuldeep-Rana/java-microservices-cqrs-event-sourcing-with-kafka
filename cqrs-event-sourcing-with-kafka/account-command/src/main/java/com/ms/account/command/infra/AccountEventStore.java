package com.ms.account.command.infra;

import com.ms.account.command.domain.EventStoreRepository;
import com.ms.cqrs.event.BaseEvent;
import com.ms.cqrs.infra.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountEventStore implements EventStore {

    private final EventStoreRepository repository;
    @Override
    public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = repository.findByAggregateIdentifier(aggregateId);

    }

    @Override
    public List<BaseEvent> getEvent(String aggregateId) {
        return null;
    }
}
