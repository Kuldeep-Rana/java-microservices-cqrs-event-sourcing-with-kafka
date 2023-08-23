package com.ms.account.command.domain;

import com.ms.account.command.api.command.OpenAccountCommand;
import com.ms.common.event.AccountClosedEvent;
import com.ms.common.event.AccountOpenedEvent;
import com.ms.common.event.FundDepositedEvent;
import com.ms.common.event.FundWithdrawnEvent;
import com.ms.cqrs.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand command){
        var event = AccountOpenedEvent.builder()
                .accountHolder(command.getAccountHolder())
                .id(command.getId())
                .createdOn(LocalDate.now())
                .version(1)
                .openingBalance(command.getOpeningBalance())
                .accountType(command.getAccountType()).build();
        raiseEvent(event);
    }

    public void apply(AccountOpenedEvent event){
        this.active = true;
        this.id = event.getId();
        this.balance = event.getOpeningBalance();
    }

    public void depositFund(double amount){
        if(this.active){
            raiseEvent(FundDepositedEvent.builder().amount(amount).id(id).build());
        }else {
            throw new IllegalStateException("Fund can not be deposited to a closed account.");
        }
    }
    public void apply(FundDepositedEvent event){
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFund(double amount){
        if(this.active){
            raiseEvent(FundWithdrawnEvent.builder().amount(amount).id(id).build());
        }else {
            throw new IllegalStateException("Fund can not be withdrawn from a closed account.");
        }
    }
    public void apply(FundWithdrawnEvent event){
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount(){
        if(this.active){
            raiseEvent(AccountClosedEvent.builder().id(id).build());
        }else {
            throw new IllegalStateException("Account is already closed.");
        }
    }
    public void apply(AccountClosedEvent event){
        this.id = event.getId();
        this.active = false;
    }
}
