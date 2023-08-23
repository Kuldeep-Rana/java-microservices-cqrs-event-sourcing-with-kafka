package com.ms.cqrs.domain;

import com.ms.cqrs.event.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public abstract class AggregateRoot {
    protected String id;
    @Setter
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();

    public List<BaseEvent> getUnCommittedChanges(){
        return  this.changes;
    }
    public void markChangesAsCommitted(){
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, boolean isNew){
        try {
            var method = getClass().getMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        }catch (NoSuchMethodException nse){
            log.warn("The apply method is not found in the aggregator for {}", event.getClass().getName());
        }catch (Exception e){
            log.error("Error applying event to aggregate", e);
        }finally {
            if(isNew){
                changes.add(event);
            }
        }
    }
    public void raiseEvent(BaseEvent event){
        applyChange(event, true);
    }
    public void replayEvent(Iterable<BaseEvent> events){
        events.forEach(event -> applyChange(event, false));
    }
}
