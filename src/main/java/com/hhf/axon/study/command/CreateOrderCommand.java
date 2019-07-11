package com.hhf.axon.study.command;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data @Builder
public class CreateOrderCommand {

    private String orderId;
    private String userId;
    private List<OrderProduct> productList;

    @Data
    public class OrderProduct{
        private String productId;
        private int buyCount;
    }
}
