package com.zilch.payments.boundary.oubound.mongo.account

import com.zilch.payments.boundary.oubound.mongo.MongoDbSliceBaseTest
import com.zilch.payments.boundary.outbound.mongo.account.MongoAccountsRepository
import com.zilch.payments.domain.account.Account
import com.zilch.payments.domain.account.AccountCreationCommand
import com.zilch.payments.domain.account.AccountStatus
import com.zilch.payments.domain.account.PersonDetails
import org.springframework.beans.factory.annotation.Autowired

class MongoAccountsRepositorySpec extends MongoDbSliceBaseTest {

    @Autowired
    MongoAccountsRepository accountRepository

    def "should save account"() {
        given:
            def account = givenAccount()
        when:
            accountRepository.save(account)
        then:
            accountRepository.findById(account.id).with {
                assert it.isPresent()
                assert it.get().status == account.status
                assert it.get().personDetails == account.personDetails
            }
    }

    static Account givenAccount(){
        def person = PersonDetails.builder()
                .firstName("First Name")
                .lastName("Last Name")
                .address("Address")
                .build()
        def command = AccountCreationCommand.builder()
                .accountStatus(AccountStatus.STANDARD)
                .personDetails(person)
                .build()
        Account.from(command)
    }
}
