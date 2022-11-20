package com.zilch.payments.boundary.inbound.controllers.account;

import com.zilch.payments.application.services.account.AccountCreationService;
import com.zilch.payments.domain.account.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountCreationService accountCreationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/accounts")
    AccountId createAccount(@RequestBody @Valid @NotNull AccountDocument accountDocument) {
        return accountCreationService.create(accountDocument.toCommand());
    }
}
