package com.zilch.payments.domain.account;

import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findById(AccountId accountId);

}
