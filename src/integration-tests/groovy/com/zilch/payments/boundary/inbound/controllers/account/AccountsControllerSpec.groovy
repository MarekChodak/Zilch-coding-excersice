package com.zilch.payments.boundary.inbound.controllers.account

import com.zilch.payments.application.services.account.AccountCreationService
import com.zilch.payments.boundary.inbound.controllers.RestIntegrationBaseSpec
import com.zilch.payments.domain.account.AccountCreationCommand
import com.zilch.payments.domain.account.AccountId
import com.zilch.payments.domain.account.AccountStatus
import org.spockframework.spring.SpringBean
import org.springframework.test.context.ContextConfiguration

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = [AccountsController.class])
class AccountsControllerSpec extends RestIntegrationBaseSpec {

    @SpringBean
    AccountCreationService accountCreationService = Mock()

    def "should create customer"() {
        given:
        def dto = givenAccountDocument()
        when:
        def result = sendPost("/accounts", dto)
        then:
        result.andExpect(status().isCreated())
        and:
            parseResponse(result, AccountId).with { given ->
                assert given.raw() == "accountId"
            }
        and:
        1 * accountCreationService.create(AccountCreationCommand.builder()
                .accountStatus(dto.status())
                .personDetails(dto.personDetailsDocument().toCommand())
                .build()) >> AccountId.of("accountId")
    }

    def "should return bad request on status validation error"() {
        given:
        def dto = givenAccountDocument(null)
        when:
        def result = sendPost("/accounts", dto)
        then:
        result.andExpect(status().isBadRequest())
    }

    def "should return bad request on person details first name validation error"() {
        given:
        def dto = givenAccountDocument(AccountStatus.NEW, '')
        when:
        def result = sendPost("/accounts", dto)
        then:
        result.andExpect(status().isBadRequest())
    }

    def "should return bad request on person details last name validation error"() {
        given:
        def dto = givenAccountDocumentWithLastName('')
        when:
        def result = sendPost("/accounts", dto)
        then:
        result.andExpect(status().isBadRequest())
    }

    def "should return bad request on person details address validation error"() {
        given:
        def dto = givenAccountDocumentWithAddress('')
        when:
        def result = sendPost("/accounts", dto)
        then:
        result.andExpect(status().isBadRequest())
    }

    static AccountDocument givenAccountDocument(AccountStatus status = AccountStatus.NEW,
                                                String firstName = "First Name",
                                                String lastName = "Last Name",
                                                String address = "address"
    ) {
        def person = PersonDetailsDocument.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .build()
        return new AccountDocument(person, status)
    }

    static AccountDocument givenAccountDocumentWithLastName(String lastName) {
        return givenAccountDocument(AccountStatus.NEW, "first", lastName)
    }

    static AccountDocument givenAccountDocumentWithAddress(String address) {
        return givenAccountDocument(AccountStatus.NEW, "first", "last", address)
    }
}
