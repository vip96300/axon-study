package com.hhf.axon.study.command.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CreateProductEvent {

    private String productId;
    private String listName;
    private long totalStock;
    private long unitPrice;
}
