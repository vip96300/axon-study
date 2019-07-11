package com.hhf.axon.study.command.handler;

import com.hhf.axon.study.command.CreateOrderCommand;
import com.hhf.axon.study.command.aggregate.OrderAggregate;
import com.hhf.axon.study.command.aggregate.ProductAggregate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Component
public class OrderHandler {

    @Autowired
    private Repository<OrderAggregate> orderAggregateRepository;
    @Autowired
    private Repository<ProductAggregate> productAggregateRepository;

    @CommandHandler
    public void createOrder(CreateOrderCommand command){
        List<OrderAggregate.OrderProduct> productList=new ArrayList<>(command.getProductList().size());
        command.getProductList().stream().forEach(p->{
            Aggregate<ProductAggregate> productAggregate=productAggregateRepository.load(p.getProductId());
            p.setUnitPrice(productAggregate.invoke(o ->o.getUnitPrice()));
            productList.add(p);
        });
        try {
            orderAggregateRepository.newInstance(()->OrderAggregate.builder().userId(command.getUserId()).products(productList).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
