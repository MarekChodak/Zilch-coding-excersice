package com.zilch.payments.domain.purchase

import com.zilch.payments.domain.account.AccountId
import spock.lang.Specification

class PurchaseSpec extends Specification {

    def "create Purchase"() {
        given:
        def command = command()
        when:
        def purchase = Purchase.from(command)
        then:
        purchase.id != null
        purchase.price == command.price()
        purchase.accountId == command.accountId()
        purchase.itemName == command.itemName()
    }

    def "make payment"() {
        given:
        def command = command()
        def purchase = Purchase.from(command)
        def paymentCommand = PaymentCommand.builder().amount(BigDecimal.TEN).build()
        when:
        purchase.makePayment(paymentCommand)
        then:
        purchase.payments.size() == 1
        purchase.payments.get(0).amount() == paymentCommand.amount()
    }

    def "calculate paid"() {
        given:
        def command = command()
        def purchase = Purchase.from(command)
        def firstPayment = paymentCommand(BigDecimal.TEN)
        def secondPayment = paymentCommand(BigDecimal.valueOf(40))
        purchase.makePayment(firstPayment)
        purchase.makePayment(secondPayment)
        when:
        def paidAmount = purchase.paidAmount()
        then:
        paidAmount == BigDecimal.valueOf(50)
    }

    static def PaymentCommand paymentCommand(BigDecimal amount) {
        PaymentCommand.builder().amount(amount).build()
    }

    static def NewPurchaseCommand command() {
        NewPurchaseCommand.builder()
                .price(BigDecimal.valueOf(100))
                .itemName("Name")
                .accountId(AccountId.of("accountID"))
                .build()
    }
}
