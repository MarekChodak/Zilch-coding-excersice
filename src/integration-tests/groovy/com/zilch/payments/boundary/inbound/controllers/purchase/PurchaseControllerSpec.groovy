package com.zilch.payments.boundary.inbound.controllers.purchase

import com.zilch.payments.application.services.purchase.PurchaseService
import com.zilch.payments.boundary.inbound.controllers.RestIntegrationBaseSpec
import com.zilch.payments.domain.account.AccountId
import com.zilch.payments.domain.purchase.NewPurchaseCommand
import com.zilch.payments.domain.purchase.PurchaseId
import org.spockframework.spring.SpringBean
import org.springframework.test.context.ContextConfiguration

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = [PurchaseController.class])
class PurchaseControllerSpec extends RestIntegrationBaseSpec {

    @SpringBean
    PurchaseService purchaseService = Mock()

    def "should create customer"() {
        given:
        def dto = newPurchaseDocument(BigDecimal.TEN, "item")
        def accountId = "accountId"
        when:
        def result = sendPost("/account/" + accountId + "/purchases", dto)
        then:
        result.andExpect(status().isCreated())
        and:
            parseResponse(result, PurchaseId).with { given ->
                assert given.raw() == "purchaseId"
            }
        and:
        1 * purchaseService.makePurchase(NewPurchaseCommand.builder()
                .accountId(AccountId.of(accountId))
                .price(dto.price())
                .itemName(dto.itemName())
                .build()) >> PurchaseId.of("purchaseId")
    }

    def "should return bad request on price validation error"() {
        given:
        def dto = newPurchaseDocument(BigDecimal.ZERO, "item")
        when:
        def result = sendPost("/account/accountId/purchases", dto)
        then:
        result.andExpect(status().isBadRequest())
    }

    def "should return bad request on item name validation error"() {
        given:
        def dto = newPurchaseDocument(BigDecimal.TEN, "")
        when:
        def result = sendPost("/account/accountId/purchases", dto)
        then:
        result.andExpect(status().isBadRequest())
    }

    static def newPurchaseDocument(BigDecimal price, String itemName){
        NewPurchaseDocument.builder()
                .price(price)
                .itemName(itemName)
                .build()
    }

}
