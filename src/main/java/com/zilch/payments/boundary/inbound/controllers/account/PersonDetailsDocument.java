package com.zilch.payments.boundary.inbound.controllers.account;

import com.zilch.payments.domain.account.PersonDetails;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public record PersonDetailsDocument(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String address
) {

    @Builder
    public PersonDetailsDocument {
    }

    public PersonDetails toCommand() {
        return PersonDetails.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .build();
    }
}
