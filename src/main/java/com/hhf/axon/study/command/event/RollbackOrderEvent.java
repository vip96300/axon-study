package com.hhf.axon.study.command.event;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/12
 * @description 回退订单
 **/
@Data @Builder
public class RollbackOrderEvent {

    private String orderId;
}
