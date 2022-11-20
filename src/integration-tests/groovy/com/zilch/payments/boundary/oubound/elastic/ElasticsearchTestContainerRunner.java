package com.zilch.payments.boundary.oubound.elastic;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.lifecycle.Startables;

import java.util.stream.Stream;

public class ElasticsearchTestContainerRunner {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            Startables.deepStart(
                    Stream.of(
                            new PaymentsElasticsearchContainer()
                    )
            ).join();
        }
    }

}

