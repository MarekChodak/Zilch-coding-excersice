package com.zilch.payments.domain.account;

import com.zilch.payments.domain.account.AccountId;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(AccountId accountId) {
        super("Could not find and account with id " + accountId.raw());
    }
}
