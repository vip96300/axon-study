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
@Entity @Table(name="t_order_info")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class OrderInfoEntity {

    @Id
    private String id;
    private String userId;
    private long totalAmount;
}
