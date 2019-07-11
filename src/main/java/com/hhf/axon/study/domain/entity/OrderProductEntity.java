package com.hhf.axon.study.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Entity @Table(name="t_order_product")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class OrderProductEntity {

    @Id
    private String id;
    private String orderId;
    private String productId;
    private int buyCount;
}
