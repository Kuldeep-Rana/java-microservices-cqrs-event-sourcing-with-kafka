package com.ms.account.command.api.command;

import com.ms.common.constant.AccountType;
import com.ms.cqrs.command.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
