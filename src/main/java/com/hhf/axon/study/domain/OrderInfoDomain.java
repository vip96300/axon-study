package com.hhf.axon.study.domain;

import com.hhf.axon.study.domain.entity.OrderInfoEntity;
import com.hhf.axon.study.domain.entity.OrderProductEntity;
import com.hhf.axon.study.domain.event.CreateOrderEvent;
import com.hhf.axon.study.domain.repository.OrderInfoRepository;
import com.hhf.axon.study.domain.repository.OrderProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Autowired
    private OrderProductRepository orderProductRepository;

    @EventHandler @Transactional(rollbackFor = Exception.class)
    public void createOrder(CreateOrderEvent event){
        OrderInfoEntity orderInfoEntity=OrderInfoEntity.builder().id(event.getOrderId()).userId(event.getUserId())
                .build();
        orderInfoRepository.save(orderInfoEntity);
        List<OrderProductEntity> orderProductEntityList=event.getProducts().stream().map(p-> OrderProductEntity.builder()
                .productId(p.getProductId())
                .buyCount(p.getBuyCount())
                .build()
        ).collect(Collectors.toList());
        orderProductRepository.saveAll(orderProductEntityList);
    }
}
