package com.zilch.payments.domain.account;

import com.zilch.payments.domain.Id;

import java.util.UUID;

public record AccountId(String raw) implements Id {

    public static AccountId of(String raw) {
        return new AccountId(raw);
    }

    public static AccountId unique() {
        return new AccountId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return raw;
    }
}
