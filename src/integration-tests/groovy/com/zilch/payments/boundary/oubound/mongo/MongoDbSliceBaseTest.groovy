package com.zilch.payments.boundary.oubound.mongo

import com.zilch.payments.application.config.mongo.MongoConfig
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ContextConfiguration(
        classes = [MongoConfig.class],
        initializers = [
                MongoDbTestContainerRunner.Initializer.class,
                MongoDbTestContainer.Initializer.class,
        ])
@Rollback
class MongoDbSliceBaseTest extends Specification {


}
