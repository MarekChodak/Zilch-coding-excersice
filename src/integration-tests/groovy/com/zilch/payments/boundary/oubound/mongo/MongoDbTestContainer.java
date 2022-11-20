package com.zilch.payments.boundary.oubound.mongo;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.lifecycle.Startable;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MongoDbTestContainer {

    static MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:4.4.10");

    static Startable getContainer() {
        return mongoDbContainer;
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongoDbContainer.getContainerIpAddress(),
                    "spring.data.mongodb.port=" + mongoDbContainer.getFirstMappedPort()

            ).applyTo(configurableApplicationContext);
        }
    }
}
