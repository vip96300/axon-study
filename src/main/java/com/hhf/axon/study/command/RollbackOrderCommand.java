package com.hhf.axon.study.command;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/12
 * @description 回退订单
 **/
@Data @Builder
public class RollbackOrderCommand {

    private String orderId;
}
