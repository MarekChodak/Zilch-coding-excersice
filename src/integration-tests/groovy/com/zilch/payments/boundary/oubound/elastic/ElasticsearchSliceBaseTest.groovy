package com.zilch.payments.boundary.oubound.elastic

import com.zilch.payments.application.config.elasticsearch.ElasticsearchConfig
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

@Testcontainers
@ContextConfiguration(
        classes = [ElasticsearchConfig.class],
        initializers = [
                ElasticsearchTestContainerRunner.Initializer.class
        ])
@Rollback
class ElasticsearchSliceBaseTest extends Specification {


}
