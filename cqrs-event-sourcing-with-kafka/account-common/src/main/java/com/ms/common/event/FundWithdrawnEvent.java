package com.ms.common.event;

import com.ms.cqrs.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FundWithdrawnEvent extends BaseEvent {
    private double amount;
}

