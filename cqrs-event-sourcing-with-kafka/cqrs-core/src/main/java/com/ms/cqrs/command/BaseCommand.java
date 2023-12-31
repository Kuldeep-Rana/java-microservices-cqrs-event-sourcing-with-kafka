package com.ms.cqrs.command;

import com.ms.cqrs.message.Message;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id){
        super(id);
    }
}
