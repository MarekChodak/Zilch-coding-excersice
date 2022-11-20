package com.zilch.payments.boundary.inbound.controllers.purchase

import com.fasterxml.jackson.core.type.TypeReference
import com.zilch.payments.application.services.purchase.AccountPurchasesSummaryService
import com.zilch.payments.boundary.inbound.controllers.RestIntegrationBaseSpec
import com.zilch.payments.domain.account.AccountId
import com.zilch.payments.domain.purchase.PurchaseSummaryRecord
import org.spockframework.spring.SpringBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = [AccountPurchasesSummaryController.class])
class AccountPurchasesSummaryControllerSpec extends RestIntegrationBaseSpec {

    @SpringBean
    AccountPurchasesSummaryService summaryService = Mock()

    def "should create customer"() {
        given:
        def accountId = "accountId"
        when:
        def result = sendGet("/account/" + accountId + "/purchases")
        then:
        result.andExpect(status().isOk())
        and:
        parseResponse(result).with { given ->
            assert given == summaryRecords()
        }
        and:
        1 * summaryService.forAccount(AccountId.of(accountId)) >> summaryRecords()
    }

    static def summaryRecords() {
        [PurchaseSummaryRecord.builder()
                 .itemName("item")
                 .price(BigDecimal.TEN)
                 .paid(BigDecimal.ONE)
                 .build()
        ]
    }

    protected List<PurchaseSummaryRecord> parseResponse(ResultActions resultAction) {
        def content = resultAction.andReturn().getResponse().getContentAsString()
        return objectMapper.readValue(content, new TypeReference<List<PurchaseSummaryRecord>>(){});
    }

}
