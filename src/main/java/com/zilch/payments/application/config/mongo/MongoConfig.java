package com.zilch.payments.application.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.LinkedList;

@Configuration
@EnableMongoRepositories(basePackages = "com.zilch.payments.boundary.outbound.mongo")
public class MongoConfig {

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    MongoCustomConversions mongoCustomConversions() {
        var converters = new LinkedList<>();
        converters.add(new IdWritingConverter());
        converters.add(new AccountIdReadingConverter());
        converters.add(new PurchaseIdReadingConverter());
        return new MongoCustomConversions(converters);
    }
}
