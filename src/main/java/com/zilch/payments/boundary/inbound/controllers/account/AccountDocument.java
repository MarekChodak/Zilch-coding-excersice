package com.zilch.payments.boundary.inbound.controllers.account;

import com.zilch.payments.domain.account.AccountCreationCommand;
import com.zilch.payments.domain.account.AccountStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record AccountDocument(
        @Valid
        @NotNull
        PersonDetailsDocument personDetailsDocument,
        @NotNull
        AccountStatus status
) {

    AccountCreationCommand toCommand(){
        return AccountCreationCommand.builder()
                .accountStatus(status)
                .personDetails(personDetailsDocument.toCommand())
                .build();
    }
}
