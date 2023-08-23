package com.ms.common.event;

import com.ms.common.constant.AccountType;
import com.ms.cqrs.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountOpenedEvent extends BaseEvent {
    private String accountHolder;
    private AccountType accountType;
    private LocalDate createdOn;
    private double openingBalance;
}
