package com.hhf.axon.study.command;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/12
 * @description 锁定商品
 **/
@Data @Builder
public class LockedProductCommand {

    private String orderId;
    private String productId;
    private int number;
}
