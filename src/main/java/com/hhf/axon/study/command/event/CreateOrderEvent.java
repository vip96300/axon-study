package com.hhf.axon.study.command.event;

import com.hhf.axon.study.command.aggregate.OrderAggregate;
import lombok.Builder;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data
@Builder
public class CreateOrderEvent {

    @TargetAggregateIdentifier
    private String orderId;
    private String userId;
    private List<OrderAggregate.OrderProduct> products;
}
