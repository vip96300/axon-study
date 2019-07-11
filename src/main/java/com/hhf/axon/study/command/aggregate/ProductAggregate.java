package com.hhf.axon.study.command.aggregate;

import com.hhf.axon.study.command.CreateProductCommand;
import com.hhf.axon.study.domain.event.CreateProductEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data @Aggregate
public class ProductAggregate {

    private static final Logger log= LoggerFactory.getLogger(ProductAggregate.class);

    @AggregateIdentifier
    private String productId;
    private String listName;
    private long totalStock;
    private long unitPrice;

    public ProductAggregate(){

    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command){
        apply(CreateProductEvent.builder().productId(this.productId).listName(command.getListName()).totalStock(command.getTotalStock()).unitPrice(command.getUnitPrice()).build());
    }

    @EventHandler
    public void on(CreateProductEvent event){
        this.productId=UUID.randomUUID().toString();
        log.info("create product event,productId:{}",productId);
        this.listName=event.getListName();
        this.totalStock=event.getTotalStock();
        this.unitPrice=event.getUnitPrice();
    }
}
