package com.hhf.axon.study.domain.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
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
}
