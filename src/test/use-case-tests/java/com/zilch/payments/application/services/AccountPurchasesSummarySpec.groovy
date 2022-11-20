package com.zilch.payments.application.services

import com.zilch.payments.application.services.purchase.AccountPurchasesSummaryService
import com.zilch.payments.domain.account.AccountId
import com.zilch.payments.domain.purchase.NewPurchaseCommand
import com.zilch.payments.domain.purchase.PaymentCommand
import com.zilch.payments.domain.purchase.Purchase
import com.zilch.payments.domain.purchase.PurchaseSummaryRecord
import spock.lang.Specification

class AccountPurchasesSummarySpec extends Specification {

    private InMemoryPurchaseRepository purchaseRepository = new InMemoryPurchaseRepository()

    AccountPurchasesSummaryService summaryService = new AccountPurchasesSummaryService(purchaseRepository)

    def "should generate purchases summary report for a given account "() {
        given: "purchases for a specific account"
        def accountId = AccountId.of("accountId")
        def firstPurchase = createFirstPurchaseForAccount(accountId)
        def secondPurchase = createSecondPurchaseForAccount(accountId)
        when: "making creating the report"
        def summaryRecords = summaryService.forAccount(accountId)
        then: "should generate summary report"
        def firstSummaryRecord = summaryRecords.find { it.purchaseId() == firstPurchase.id }
        verifySummaryRecord(firstSummaryRecord, firstPurchase)
        def secondSummaryRecord = summaryRecords.find { it.purchaseId() == secondPurchase.id }
        verifySummaryRecord(secondSummaryRecord, secondPurchase)
    }

    static void verifySummaryRecord(PurchaseSummaryRecord firstSummaryRecord, Purchase firstPurchase) {
        assert firstSummaryRecord.price() == firstPurchase.price
        assert firstSummaryRecord.itemName() == firstPurchase.itemName
        assert firstSummaryRecord.paid() == firstPurchase.paidAmount()
    }

    def createFirstPurchaseForAccount(AccountId accountId) {
        def purchaseCommand = NewPurchaseCommand.builder().accountId(accountId)
                .price(BigDecimal.TEN)
                .itemName("Item")
                .build()
        def purchase = Purchase.from(purchaseCommand)
        purchase.makePayment(paymentCommand(BigDecimal.ONE))
        purchaseRepository.save(purchase)
        purchase
    }

    def createSecondPurchaseForAccount(AccountId accountId) {
        def purchaseCommand = NewPurchaseCommand.builder().accountId(accountId)
                .price(BigDecimal.valueOf(100))
                .itemName("Second Item")
                .build()
        def purchase = Purchase.from(purchaseCommand)
        purchase.makePayment(paymentCommand(BigDecimal.valueOf(50)))
        purchaseRepository.save(purchase)
        purchase

    }

    static def paymentCommand(BigDecimal value) {
        PaymentCommand.builder().amount(value).build()
    }
}
