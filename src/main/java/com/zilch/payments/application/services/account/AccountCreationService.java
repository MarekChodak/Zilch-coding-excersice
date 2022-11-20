package com.zilch.payments.application.services.account;

import com.zilch.payments.domain.account.Account;
import com.zilch.payments.domain.account.AccountCreationCommand;
import com.zilch.payments.domain.account.AccountId;
import com.zilch.payments.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCreationService {

    private final AccountRepository accountRepository;

    public AccountId create(AccountCreationCommand accountCreationCommand) {
        Account account = Account.from(accountCreationCommand);
        accountRepository.save(account);
        return account.getId();
    }

}
