package com.hhf.axon.study.command.handler;

import com.hhf.axon.study.command.CreateUserCommand;
import com.hhf.axon.study.command.aggregate.UserAggregate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Component
public class UserHandler {

    @Autowired
    private Repository<UserAggregate> userAggregateRepository;

    @CommandHandler
    public void createUser(CreateUserCommand command){
        try {
            userAggregateRepository.newInstance(()-> UserAggregate.builder()
                    .userId(UUID.randomUUID().toString())
                    .userName(command.getUserName())
                    .password(command.getPassword())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
