package com.hhf.axon.study.command.config;

import com.hhf.axon.study.command.aggregate.UserAggregate;
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
public class UserConfig {

    @Autowired
    private EventStore eventStore;

    @Bean
    @Scope("prototype")
    public UserAggregate userAggregate(){
        return new UserAggregate();
    }

    @Bean
    public AggregateFactory<UserAggregate> userAggregateFactory(){
        SpringPrototypeAggregateFactory<UserAggregate> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("userAggregate");
        return aggregateFactory;
    }

    @Bean
    public Repository<UserAggregate> userAggregateRepository(){
        EventSourcingRepository<UserAggregate> repository = new EventSourcingRepository<>(
                userAggregateFactory(),
                eventStore
        );
        return repository;
    }
}
