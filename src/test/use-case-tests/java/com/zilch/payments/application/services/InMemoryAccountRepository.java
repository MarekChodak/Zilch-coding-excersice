package com.zilch.payments.application.services;

import com.zilch.payments.domain.account.Account;
import com.zilch.payments.domain.account.AccountId;
import com.zilch.payments.domain.account.AccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {

    private final Map<AccountId, Account> db = new HashMap<>();

    @Override
    public Account save(Account account) {
        return db.put(account.getId(), account);
    }

    @Override
    public Optional<Account> findById(AccountId accountId) {
        return Optional.ofNullable(db.get(accountId));
    }
}
