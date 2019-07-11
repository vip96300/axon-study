package com.hhf.axon.study.command.aggregate;

import com.hhf.axon.study.command.CreateOrderCommand;
import com.hhf.axon.study.domain.event.CreateOrderEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;
import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data @Aggregate @Builder @AllArgsConstructor
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String userId;
    private long totalPrice;
    @AggregateMember
    private List<OrderProduct> products;

    public OrderAggregate(){
        this.orderId= UUID.randomUUID().toString();
        apply(CreateOrderEvent.builder().orderId(this.orderId).userId(this.userId)
                .products(products)
                .build());
    }

    @EventHandler
    public void on(CreateOrderEvent event){
        this.orderId=event.getOrderId();
        this.userId=event.getUserId();
        mathTotalPrice();
    }

    private void mathTotalPrice(){
        this.totalPrice=products.stream().mapToLong(p->p.getBuyCount()*p.getUnitPrice()).sum();
    }

    @Data
    public class OrderProduct{
        private String productId;
        private int buyCount;
        private long unitPrice;
    }
}