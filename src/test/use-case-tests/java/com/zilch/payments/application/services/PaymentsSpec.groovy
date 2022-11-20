package com.zilch.payments.application.services

import com.zilch.payments.application.services.purchase.PurchaseService
import com.zilch.payments.domain.account.AccountId
import com.zilch.payments.domain.purchase.NewPurchaseCommand
import com.zilch.payments.domain.purchase.PaymentCommand
import com.zilch.payments.domain.purchase.Purchase
import com.zilch.payments.domain.purchase.PurchaseId
import spock.lang.Specification

class PaymentsSpec extends Specification{

    private InMemoryAccountRepository accountRepository = new InMemoryAccountRepository()

    private InMemoryPurchaseRepository purchaseRepository = new InMemoryPurchaseRepository()

    private def purchaseService = new PurchaseService(accountRepository, purchaseRepository)

    def "should make payments for a purchase"() {
        given: "made purchase"
        def purchase = createPurchase()
        and: "payment request"
        def paymentCommand = paymentCommand(purchase.id)
        when: "making payment"
        purchaseService.makePayment(paymentCommand)
        then: "payment is registered"
        def possiblePurchase = purchaseRepository.findById(purchase.id)
        possiblePurchase.isPresent()
        def foundPurchase = possiblePurchase.get()
        purchase.payments.size() == 1
        purchase.payments.get(0).amount() == paymentCommand.amount()
        purchase.payments.get(0).madeAt() != null
    }

    static def paymentCommand(PurchaseId purchaseId){
        return PaymentCommand.builder()
                .amount(BigDecimal.TEN)
                .purchaseId(purchaseId)
                .build()
    }

    def createPurchase() {
        def purchaseCommand = NewPurchaseCommand.builder().accountId(AccountId.of("accountId"))
                .price(BigDecimal.TEN)
                .itemName("Item")
                .build()
        def purchase = Purchase.from(purchaseCommand)
        purchaseRepository.save(purchase)
        purchase
    }
}
