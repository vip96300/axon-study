package com.hhf.axon.study.domain.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Entity @Table(name="t_product_info") @Data @Builder
public class ProductInfoEntity {

    @Id
    private String id;
    private String listName;
    private long totalStock;
    private long unitPrice;
}
