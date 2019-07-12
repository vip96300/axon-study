package com.hhf.axon.study.command.event;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/12
 * @description
 **/
@Data @Builder
public class NotEnoughProductEvent {

    private String orderId;
    private String productId;
}
