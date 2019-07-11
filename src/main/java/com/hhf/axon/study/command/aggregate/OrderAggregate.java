package com.hhf.axon.study.command.aggregate;

import com.hhf.axon.study.command.CreateOrderCommand;
import com.hhf.axon.study.domain.event.CreateOrderEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data @Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String userId;
    private long totalPrice;
    @AggregateMember
    private List<OrderProduct> products;

    public OrderAggregate(){
    }

    public OrderAggregate(String orderId, String userId,List<OrderProduct> products) {
        apply(CreateOrderEvent.builder().orderId(orderId).userId(userId)
                .products(products)
                .build());
    }

    @EventHandler
    public void on(CreateOrderEvent event){
        this.orderId=event.getOrderId();
        this.userId=event.getUserId();
        this.products=event.getProducts();
        mathTotalPrice();
    }

    private void mathTotalPrice(){
        this.totalPrice=products.stream().mapToLong(p->p.getBuyCount()*p.getUnitPrice()).sum();
    }

    @Data @NoArgsConstructor
    public static class OrderProduct{
        private String productId;
        private int buyCount;
        private long unitPrice;
    }
}
