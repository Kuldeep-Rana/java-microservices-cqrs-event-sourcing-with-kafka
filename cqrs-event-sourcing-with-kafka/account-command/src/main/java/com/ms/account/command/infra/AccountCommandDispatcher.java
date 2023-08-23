package com.ms.account.command.infra;

import com.ms.cqrs.command.BaseCommand;
import com.ms.cqrs.command.CommandHandlerMethod;
import com.ms.cqrs.infra.CommandDispatcher;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AccountCommandDispatcher implements CommandDispatcher {
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();
    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }
    @Override
    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if(CollectionUtils.isEmpty(handlers)){
            throw  new RuntimeException("No Handler was registered.");
        }
        if(handlers.size() > 1){
            throw new RuntimeException("Can send command to more than one handler");
        }
        handlers.get(0).handle(command);
    }
}
