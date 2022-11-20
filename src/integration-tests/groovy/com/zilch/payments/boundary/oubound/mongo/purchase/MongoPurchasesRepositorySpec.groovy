package com.zilch.payments.boundary.oubound.mongo.purchase

import com.zilch.payments.boundary.oubound.mongo.MongoDbSliceBaseTest
import com.zilch.payments.boundary.outbound.mongo.purchase.MongoPurchasesRepository
import com.zilch.payments.domain.account.AccountId
import com.zilch.payments.domain.purchase.NewPurchaseCommand
import com.zilch.payments.domain.purchase.Purchase
import org.springframework.beans.factory.annotation.Autowired

class MongoPurchasesRepositorySpec extends MongoDbSliceBaseTest {

    @Autowired
    MongoPurchasesRepository purchasesRepository

    def "should save purchase"() {
        given:
            def purchase = givenPurchase(AccountId.of("accountId"))
        when:
            purchasesRepository.save(purchase)
        then:
            purchasesRepository.findById(purchase.id).with {
                assert it.isPresent()
                assert it.get().itemName == purchase.itemName
                assert it.get().accountId == purchase.accountId
                assert it.get().price == purchase.price
            }
    }

    def "should find all purchases for a specific account"() {
        given:
        def accountId = AccountId.of("accountId")
        def purchase = givenPurchase(accountId)
        def purchaseTwo = givenPurchase(accountId)
        def purchaseThree = givenPurchase(AccountId.of("accountIdTwo"))
        when:
        purchasesRepository.save(purchase)
        purchasesRepository.save(purchaseTwo)
        purchasesRepository.save(purchaseThree)
        then:
        purchasesRepository.findByAccountId(accountId).with {
            assert it.size() == 2
            assert it.findAll {it.id == purchase.id}.size() == 1
            assert it.findAll {it.id == purchaseTwo.id}.size() == 1
        }
    }

    def cleanup() {
        purchasesRepository.deleteAll()
    }

    static Purchase givenPurchase(AccountId accountId){
        def purchaseCommand = NewPurchaseCommand.builder()
                .accountId(accountId)
                .itemName("item")
                .price(BigDecimal.TEN)
                .build()
        Purchase.from(purchaseCommand)
    }
}
