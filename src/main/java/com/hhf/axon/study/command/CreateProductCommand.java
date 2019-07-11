package com.hhf.axon.study.command;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data @Builder
public class CreateProductCommand {

    private String productId;
    private String listName;
    private long totalStock;
    private long unitPrice;
}
