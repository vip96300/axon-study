package com.hhf.axon.study.command.event;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/12
 * @description 锁定商品
 **/
@Data @Builder
public class LockedProductEvent {

    private String orderId;
    private String productId;
    private int number;
}
