package com.hhf.axon.study.command.aggregate;

import com.hhf.axon.study.command.CreateOrderCommand;
import com.hhf.axon.study.domain.event.CreateOrderEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data @Aggregate
public class OrderRoot {

    @AggregateIdentifier
    private String orderId;
    private String userId;
    private long totalAmount;
    private List<CreateOrderCommand.OrderProduct> products;

    public OrderRoot(){

    }

    @CommandHandler
    public void createOrder(CreateOrderCommand command){
        apply(CreateOrderEvent.builder().orderId(command.getOrderId()).userId(command.getUserId()).build());
    }

    @EventHandler
    public void on(CreateOrderEvent event){
        this.orderId=event.getOrderId();
        this.userId=event.getUserId();
        this.totalAmount=0;
        this.products=event.getProducts();
    }
}
