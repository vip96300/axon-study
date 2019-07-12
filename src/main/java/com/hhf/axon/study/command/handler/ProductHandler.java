package com.hhf.axon.study.command.handler;

import com.hhf.axon.study.command.CreateProductCommand;
import com.hhf.axon.study.command.LockedProductCommand;
import com.hhf.axon.study.command.aggregate.ProductAggregate;
import com.hhf.axon.study.command.event.LockedProductEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Component
public class ProductHandler {

    @Autowired
    private Repository<ProductAggregate> productAggregateRepository;

    @CommandHandler
    public void on(CreateProductCommand command){
        try {
            productAggregateRepository.newInstance(()->
                    new ProductAggregate(UUID.randomUUID().toString(),command.getListName(),command.getTotalStock(),command.getUnitPrice())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CommandHandler
    public void on(LockedProductCommand command){
        Aggregate<ProductAggregate> productAggregate=productAggregateRepository.load(command.getProductId());
        productAggregate.execute((root)->root.locked(command.getOrderId(),command.getNumber()));
    }
}
