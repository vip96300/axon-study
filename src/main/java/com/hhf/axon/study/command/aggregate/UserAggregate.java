package com.hhf.axon.study.command.aggregate;

import com.hhf.axon.study.domain.event.CreateUserEvent;
import com.hhf.axon.study.domain.event.UpdatePasswordEvent;
import com.hhf.axon.study.command.CreateUserCommand;
import com.hhf.axon.study.command.UpdatePasswordCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Data @Builder @AllArgsConstructor
@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String userId;
    private String userName;
    private String password;

    public UserAggregate(){
        apply(CreateUserEvent.builder().userId(this.userId).userName(userName).password(password).build());
    }

    @CommandHandler
    public void UpdatePassword(UpdatePasswordCommand command){
        apply(UpdatePasswordEvent.builder().userId(this.userId).password(command.getPassword()).build());
    }

    @EventHandler
    public void on(CreateUserEvent event){
        this.userId= event.getUserId();
        this.userName=event.getUserName();
        this.password=event.getPassword();
    }

    @EventHandler
    public void on(UpdatePasswordEvent event){
        this.password=event.getPassword();
    }
}
