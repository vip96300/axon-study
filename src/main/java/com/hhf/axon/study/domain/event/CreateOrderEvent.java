package com.hhf.axon.study.domain.event;

import com.hhf.axon.study.command.CreateOrderCommand;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data
@Builder
public class CreateOrderEvent {

    private String orderId;
    private String userId;
    private List<CreateOrderCommand.OrderProduct> products;
}
