package com.hhf.axon.study.interfaces.controller;

import com.hhf.axon.study.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@RestController
@RequestMapping(path="/product/info")
public class ProductInfoController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path="/createProduct")
    public void createProduct(@RequestParam(name="listName")String listName,@RequestParam(name="totalStock")long totalStock,@RequestParam(name="unitPrice")long unitPrice){
        commandGateway.sendAndWait(CreateProductCommand.builder().listName(listName).totalStock(totalStock).unitPrice(unitPrice).build());
    }
}
