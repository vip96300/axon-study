package com.hhf.axon.study.command.sagas;

import com.hhf.axon.study.command.LockedProductCommand;
import com.hhf.axon.study.command.aggregate.OrderAggregate;
import com.hhf.axon.study.command.event.CreateOrderEvent;
import com.hhf.axon.study.command.event.NotEnoughProductEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author huang hong fei
 * @date 2019/7/12
 * @description
 **/
@Saga
public class CreateOrderSaga {

    private String orderId;
    private List<OrderAggregate.OrderProduct> lockedList;
    private List<OrderAggregate.OrderProduct> unlockList;
    /**
     * 是否需要回滚订单
     */
    private boolean isNeedRollbackOrder;

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(CreateOrderEvent event){
        this.orderId=event.getOrderId();
        this.lockedList=event.getProducts();
        event.getProducts().stream().forEach(p->{
            commandGateway.send(LockedProductCommand.builder().orderId(orderId).number(p.getBuyCount()).productId(p.getProductId()).build());
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(NotEnoughProductEvent event){

    }
}
