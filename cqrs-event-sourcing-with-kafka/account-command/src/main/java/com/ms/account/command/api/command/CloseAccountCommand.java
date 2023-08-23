package com.ms.account.command.api.command;

import com.ms.cqrs.command.BaseCommand;

public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id){
        super(id);
    }
}
