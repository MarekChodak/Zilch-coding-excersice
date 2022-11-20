package com.zilch.payments.domain.account;

import lombok.Builder;

public record PersonDetails(
        String firstName,
        String lastName,
        String address
) {

    @Builder
    public PersonDetails {
    }
}
