package com.zilch.payments.application.config.mongo;

import com.zilch.payments.domain.account.AccountId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class AccountIdReadingConverter implements Converter<String, AccountId> {

    @Override
    public AccountId convert(@Nullable String source) {
        return isNotBlank(source) ? new AccountId(source) : null;
    }
}
