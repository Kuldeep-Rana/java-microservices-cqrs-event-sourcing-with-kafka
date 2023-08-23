package com.ms.account.command.api.command;

import com.ms.cqrs.command.BaseCommand;
import lombok.Data;

@Data
public class DepositFundCommand extends BaseCommand {
    private double amount;
}
