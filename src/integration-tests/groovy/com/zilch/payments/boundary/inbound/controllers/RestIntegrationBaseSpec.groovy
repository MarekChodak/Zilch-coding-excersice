package com.zilch.payments.boundary.inbound.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.zilch.payments.application.config.jackson.JacksonConfig
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@Slf4j
@ActiveProfiles("integrationtest")
@WebMvcTest()
@AutoConfigureMockMvc()
@ContextConfiguration(classes = [
        JacksonConfig.class
])
abstract class RestIntegrationBaseSpec extends Specification {

    @Autowired
    WebApplicationContext context

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MockMvc mockMvc

    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build()
    }

    protected <T> T parseResponse(ResultActions resultAction, Class<T> responseClass) {
        def content = resultAction.andReturn().getResponse().getContentAsString()
        return objectMapper.readValue(content, responseClass)
    }

    protected ResultActions sendPost(String url, Object body) throws Exception {
        def requestBuilder = MockMvcRequestBuilders.post(url)
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(body))
        return mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
    }

    protected ResultActions sendGet(String url) throws Exception {
        def requestBuilder = MockMvcRequestBuilders.get(url)
        return mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
    }
}
