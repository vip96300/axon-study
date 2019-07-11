package com.hhf.axon.study.interfaces.controller;

import com.hhf.axon.study.command.CreateUserCommand;
import com.hhf.axon.study.command.UpdatePasswordCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@RestController
@RequestMapping(path="/user/info")
public class UserInfoController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path="/createUser")
    public void createUser(@RequestParam(name="userName")String userName,@RequestParam(name="password")String password){
        commandGateway.sendAndWait(CreateUserCommand.builder().userName(userName).password(password).build());
    }

    @PostMapping(path="/updatePassword")
    public void updatePassword(@RequestParam(name="userId")String userId,@RequestParam(name="password")String password){
        commandGateway.sendAndWait(UpdatePasswordCommand.builder().userId(userId).password(password).build());
    }
}
