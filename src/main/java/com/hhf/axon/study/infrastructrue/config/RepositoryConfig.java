package com.hhf.axon.study.infrastructrue.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
@Configuration
public class RepositoryConfig {

    @Value("${spring.data.mongodb.host}")
    private String mongodbHost;
    @Value("${spring.data.mongodb.port}")
    private int mongodbPort;
    @Value("${spring.data.mongodb.database}")
    private String mongodbName;
    @Value("${spring.data.mongodb.username}")
    private String mongodbUsername;
    @Value("${spring.data.mongodb.password}")
    private String mongodbPassword;
    private final String domainEventsCollectionName="axonDomainEvents";
    private final String snapshotEventsCollectionName="axonSnapshotEvents";
    private final String sagasCollectionName="axonSagas";

    @Bean
    public Serializer axonSerializer(){
        return new JacksonSerializer();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(){
        return new MongoEventStorageEngine(axonSerializer(),null, axonMongoTemplate(), new DocumentPerEventStorageStrategy());
    }

    @Bean(name = "axonMongoTemplate")
    public MongoTemplate axonMongoTemplate() {
        MongoTemplate template = new DefaultMongoTemplate(mongoClient(), mongodbName, domainEventsCollectionName, snapshotEventsCollectionName);
        return template;
    }

    @Bean
    public MongoClient mongoClient(){
        MongoFactory mongoFactory = new MongoFactory();
        mongoFactory.setMongoAddresses(Arrays.asList(new ServerAddress(mongodbHost,mongodbPort)));
        return mongoFactory.createMongo();
    }

    @Bean
    public SagaStore sagaStore(){
        org.axonframework.mongo.eventhandling.saga.repository.MongoTemplate mongoTemplate = new org.axonframework.mongo.eventhandling.saga.repository.DefaultMongoTemplate(mongoClient(), mongodbName, sagasCollectionName);
        return new MongoSagaStore(mongoTemplate, axonSerializer());
    }

}
