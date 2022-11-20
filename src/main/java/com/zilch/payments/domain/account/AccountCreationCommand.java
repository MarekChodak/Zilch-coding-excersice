package com.zilch.payments.domain.account;

import lombok.Builder;

public record AccountCreationCommand(
        AccountStatus accountStatus,
        PersonDetails personDetails
) {

    @Builder
    public AccountCreationCommand {
    }
}
