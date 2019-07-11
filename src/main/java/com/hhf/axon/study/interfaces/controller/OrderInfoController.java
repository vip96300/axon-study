package com.hhf.axon.study.interfaces.controller;

import com.hhf.axon.study.command.CreateOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@RestController
@RequestMapping(path="/order/info")
public class OrderInfoController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path="/createOrder")
    public void createOrder(@RequestBody CreateOrderCommand command){
        commandGateway.sendAndWait(command);
    }
}
