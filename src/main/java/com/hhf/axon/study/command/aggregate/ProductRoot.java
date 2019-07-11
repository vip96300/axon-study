package com.hhf.axon.study.command.aggregate;

import com.hhf.axon.study.command.CreateProductCommand;
import com.hhf.axon.study.domain.event.CreateProductEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Data @Aggregate
public class ProductRoot {

    @AggregateIdentifier
    private String productId;
    private String listName;
    private long totalStock;
    private long unitPrice;

    public ProductRoot(){

    }

    @CommandHandler
    public ProductRoot(CreateProductCommand command){
        apply(CreateProductEvent.builder().productId(command.getProductId()).listName(command.getListName()).totalStock(command.getTotalStock()).unitPrice(command.getUnitPrice()));
    }

    @EventHandler
    public void on(CreateProductEvent event){
        this.productId=event.getProductId();
        this.listName=event.getListName();
        this.totalStock=event.getTotalStock();
        this.unitPrice=event.getUnitPrice();
    }
}
