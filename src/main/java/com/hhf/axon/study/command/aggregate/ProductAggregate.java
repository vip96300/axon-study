package com.hhf.axon.study.command.aggregate;

import com.hhf.axon.study.command.LockedProductCommand;
import com.hhf.axon.study.command.event.CreateProductEvent;
import com.hhf.axon.study.command.event.LockedProductEvent;
import com.hhf.axon.study.command.event.NotEnoughProductEvent;
import com.hhf.axon.study.command.event.UnlockProductEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public ProductAggregate(String productId, String listName, long totalStock, long unitPrice) {
        apply(CreateProductEvent.builder().productId(productId).listName(listName).totalStock(totalStock).unitPrice(unitPrice).build());
    }

    @EventHandler
    public void on(CreateProductEvent event){
        this.productId=event.getProductId();
        this.listName=event.getListName();
        this.totalStock=event.getTotalStock();
        this.unitPrice=event.getUnitPrice();
    }

    public void locked(String orderId,int number){
        if(totalStock>=number){
            apply(LockedProductEvent.builder().orderId(orderId).productId(productId).number(number).build());
        }else{
            apply(NotEnoughProductEvent.builder().orderId(orderId).productId(productId).build());
        }
    }

    @EventHandler
    public void on(LockedProductEvent event){
        this.totalStock-=event.getNumber();
    }

    @EventHandler
    public void on(UnlockProductEvent event){
        this.totalStock+=event.getNumber();
    }
}
