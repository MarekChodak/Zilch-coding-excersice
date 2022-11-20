package com.zilch.payments.application.services

import com.zilch.payments.application.services.account.AccountCreationService
import com.zilch.payments.domain.account.AccountCreationCommand
import com.zilch.payments.domain.account.AccountStatus
import com.zilch.payments.domain.account.PersonDetails
import spock.lang.Specification

class AccountCreationSpec extends Specification {

    private InMemoryAccountRepository accountRepository = new InMemoryAccountRepository()

    private AccountCreationService accountCreationService = new AccountCreationService(accountRepository)

    def "should store account"() {
        given: "Given a account creation request"
        def accountCreationCommand = creationRequest()
        when: "Request is processed"
        def accountId = accountCreationService.create(accountCreationCommand)
        then: "account should be stored"
        def possibleAccount = accountRepository.findById(accountId)
        possibleAccount.isPresent()
        def account = possibleAccount.get()
        account.id == accountId
        account.status == accountCreationCommand.accountStatus()
        account.personDetails == accountCreationCommand.personDetails()
    }

    static AccountCreationCommand creationRequest() {
        AccountCreationCommand.builder()
                .accountStatus(AccountStatus.NEW)
                .personDetails(PersonDetails.builder()
                        .firstName("first Name")
                        .lastName("last Name")
                        .address("address")
                        .build()
                ).build()
    }
}
