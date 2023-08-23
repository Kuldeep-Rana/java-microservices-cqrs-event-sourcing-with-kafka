package com.ms.cqrs.infra;

import com.ms.cqrs.command.BaseCommand;
import com.ms.cqrs.command.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
