package com.hhf.axon.study.command;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/12
 * @description
 **/
@Data @Builder
public class ConfirmOrderCommand {

    private String orderId;
}
