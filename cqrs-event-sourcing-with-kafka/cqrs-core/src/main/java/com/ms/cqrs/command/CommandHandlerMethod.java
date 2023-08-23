package com.ms.cqrs.command;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
     void handle(T t);
}
