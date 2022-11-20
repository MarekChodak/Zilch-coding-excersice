package com.zilch.payments.domain.account

import spock.lang.Specification

import static com.zilch.payments.domain.account.AccountStatus.BLACKLISTED
import static com.zilch.payments.domain.account.AccountStatus.NEW
import static com.zilch.payments.domain.account.AccountStatus.STANDARD
import static com.zilch.payments.domain.account.AccountStatus.VIP

class AccountSpec extends Specification {

    def "should create Account"() {
        given:
        def accountCreationCommand = command()
        when:
        def account = Account.from(accountCreationCommand)
        then:
        account.id != null
        account.status == accountCreationCommand.accountStatus()
        account.personDetails == accountCreationCommand.personDetails()
    }

    def "should not allow purchases if account status is BLACKLISTED"() {
        given:
        def accountCreationCommand = command(status)
        def account = Account.from(accountCreationCommand)
        when:
        def purchaseAllowed = account.isPurchaseAllowed()
        then:
        purchaseAllowed == result
        where:
        status      | result
        BLACKLISTED | false
        NEW         | true
        STANDARD    | true
        VIP         | true
    }

    static def command(AccountStatus status = NEW) {
        AccountCreationCommand.builder()
                .accountStatus(status)
                .personDetails(PersonDetails.builder()
                        .address("address").
                        firstName("first Name")
                        .lastName("last Name")
                        .build()
                ).build()
    }
}
