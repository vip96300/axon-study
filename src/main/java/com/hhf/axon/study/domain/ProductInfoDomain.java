package com.hhf.axon.study.domain;

import com.hhf.axon.study.domain.entity.ProductInfoEntity;
import com.hhf.axon.study.command.event.CreateProductEvent;
import com.hhf.axon.study.domain.repository.ProductInfoRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Component
public class ProductInfoDomain {

    private static final Logger log= LoggerFactory.getLogger(ProductInfoDomain.class);

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @EventHandler @Transactional(rollbackFor = Exception.class)
    public void createProduct(CreateProductEvent event){
        ProductInfoEntity productInfoEntity=ProductInfoEntity.builder().id(event.getProductId()).listName(event.getListName()).unitPrice(event.getUnitPrice()).totalStock(event.getTotalStock()).build();
        productInfoRepository.save(productInfoEntity);
    }
}
