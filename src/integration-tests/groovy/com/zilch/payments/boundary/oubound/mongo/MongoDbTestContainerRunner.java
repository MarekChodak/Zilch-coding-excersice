package com.zilch.payments.boundary.oubound.mongo;

import org.testcontainers.lifecycle.Startables;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MongoDbTestContainerRunner {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            Startables.deepStart(
                    Stream.of(
                            MongoDbTestContainer.getContainer()
                    )
            ).join();
        }
    }

}

