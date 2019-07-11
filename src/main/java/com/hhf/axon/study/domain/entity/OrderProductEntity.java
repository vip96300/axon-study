package com.hhf.axon.study.domain.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Entity @Table(name="t_order_product") @Data @Builder
public class OrderProductEntity {

    @Id
    @GeneratedValue
    private String id;
    private String orderId;
    private String productId;
    private int buyCount;
}
