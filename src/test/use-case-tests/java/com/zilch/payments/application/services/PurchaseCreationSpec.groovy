package com.zilch.payments.application.services

import com.zilch.payments.application.services.purchase.PurchaseService
import com.zilch.payments.domain.account.*
import com.zilch.payments.domain.purchase.NewPurchaseCommand
import com.zilch.payments.domain.purchase.PurchaseNotAllowedException
import spock.lang.Specification

class PurchaseCreationSpec extends Specification {

    private InMemoryAccountRepository accountRepository = new InMemoryAccountRepository()

    private InMemoryPurchaseRepository purchaseRepository = new InMemoryPurchaseRepository()

    private InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher()

    private def purchaseService = new PurchaseService(accountRepository, purchaseRepository, eventPublisher)

    def "should allow to make purchase for not blacklisted accounts"() {
        given: "not a new account"
        def accountId = createAccountWithStatus(AccountStatus.NEW)
        and: "purchase request"
        def purchaseCommand = purchaseCommand(accountId)
        when: "making the purchase"
        def purchaseId = purchaseService.makePurchase(purchaseCommand)
        then: "new purchase is stored"
        def possiblePurchase = purchaseRepository.findById(purchaseId)
        possiblePurchase.isPresent()
        def purchase = possiblePurchase.get()
        purchase.accountId == accountId
        purchase.itemName == purchaseCommand.itemName()
        purchase.price == purchaseCommand.price()
        and: "event is published"
        eventPublisher.getPublishedEvents().size() == 1
    }

    def "should NOT allow to make purchase for not blacklisted accounts"() {
        given: "not a new account"
        def accountId = createAccountWithStatus(AccountStatus.BLACKLISTED)
        and: "purchase request"
        def purchaseCommand = purchaseCommand(accountId)
        when: "making the purchase"
        purchaseService.makePurchase(purchaseCommand)
        then: "purchase not allowed error is thrown"
        thrown(PurchaseNotAllowedException)
    }

    static def purchaseCommand(AccountId accountId) {
        NewPurchaseCommand.builder().accountId(accountId).price(BigDecimal.TEN).itemName("Item").build()
    }

    AccountId createAccountWithStatus(AccountStatus accountStatus) {
        def command = AccountCreationCommand.builder()
                .accountStatus(accountStatus)
                .personDetails(PersonDetails.builder()
                        .firstName("first Name")
                        .lastName("last Name")
                        .address("address")
                        .build()
                ).build()
        def account = Account.from(command)
        accountRepository.save(account)
        account.id
    }
}
