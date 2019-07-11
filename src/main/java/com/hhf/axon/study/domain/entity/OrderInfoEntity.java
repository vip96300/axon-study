package com.hhf.axon.study.domain.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Entity @Table(name="t_order_info")@Data @Builder
public class OrderInfoEntity {

    @Id
    private String id;
    private String userId;
    private long totalAmount;
    @OneToMany(fetch= FetchType.EAGER)
    @JoinTable(name="t_order_products",joinColumns={@JoinColumn(name="order_id")},inverseJoinColumns={@JoinColumn(name="order_product_id")})
    private List<OrderProductEntity> products;
}
