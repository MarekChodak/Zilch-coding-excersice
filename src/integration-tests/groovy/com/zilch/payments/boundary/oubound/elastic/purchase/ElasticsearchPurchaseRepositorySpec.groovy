package com.zilch.payments.boundary.oubound.elastic.purchase

import com.zilch.payments.boundary.oubound.elastic.ElasticsearchSliceBaseTest
import com.zilch.payments.boundary.outbound.elastic.ElasticsearchPurchaseRepository
import com.zilch.payments.domain.purchase.PurchaseView
import org.springframework.beans.factory.annotation.Autowired

class ElasticsearchPurchaseRepositorySpec extends ElasticsearchSliceBaseTest {

    @Autowired
    ElasticsearchPurchaseRepository purchaseRepository

    def "should save purchase view"() {
        given:
        def purchaseView = givenPurchaseView()
        when:
        purchaseRepository.save(purchaseView)
        then:
        purchaseRepository.findById(purchaseView.purchaseId).with {
            assert it.isPresent()
            assert it.get().itemName == purchaseView.itemName
            assert it.get().price == purchaseView.price
        }
    }

    static def givenPurchaseView() {
        PurchaseView.builder()
                .purchaseId("purchaseId")
                .itemName("Item")
                .price(20d)
                .build()
    }
}
