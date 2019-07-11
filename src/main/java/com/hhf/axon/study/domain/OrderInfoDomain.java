package com.hhf.axon.study.domain;

import com.hhf.axon.study.domain.entity.OrderInfoEntity;
import com.hhf.axon.study.domain.entity.OrderProductEntity;
import com.hhf.axon.study.domain.event.CreateOrderEvent;
import com.hhf.axon.study.domain.repository.OrderInfoRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Component
public class OrderInfoDomain {

    private static final Logger log= LoggerFactory.getLogger(OrderInfoDomain.class);

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @EventHandler @Transactional(rollbackFor = Exception.class)
    public void createOrder(CreateOrderEvent event){
        OrderInfoEntity orderInfoEntity=OrderInfoEntity.builder().id(event.getOrderId()).userId(event.getUserId())
                .products(event.getProducts().stream().map(p->{
                    OrderProductEntity orderProductEntity=OrderProductEntity.builder().productId(p.getProductId())
                            .buyCount(p.getBuyCount()).build();
                    return orderProductEntity;
                }).collect(Collectors.toList()))
                .build();
        orderInfoRepository.save(orderInfoEntity);
    }
}
