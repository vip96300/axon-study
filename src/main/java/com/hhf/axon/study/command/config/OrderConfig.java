package com.hhf.axon.study.command.config;

import com.hhf.axon.study.command.aggregate.OrderAggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Configuration
public class OrderConfig {

    @Autowired
    private EventStore eventStore;

    @Bean
    @Scope("prototype")
    public OrderAggregate orderAggregate(){
        return new OrderAggregate();
    }

    @Bean
    public AggregateFactory<OrderAggregate> orderAggregateAggregateFactory(){
        SpringPrototypeAggregateFactory<OrderAggregate> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("orderAggregate");
        return aggregateFactory;
    }

    @Bean
    public Repository<OrderAggregate> orderAggregateRepository(){
        EventSourcingRepository<OrderAggregate> repository = new EventSourcingRepository<OrderAggregate>(
                orderAggregateAggregateFactory(),
                eventStore
        );
        return repository;
    }
}
