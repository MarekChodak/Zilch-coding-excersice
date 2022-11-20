package com.zilch.payments.domain.purchase;

import com.zilch.payments.domain.Id;

import java.util.UUID;

public record PurchaseId(String raw) implements Id {

    public static PurchaseId of(String raw) {
        return new PurchaseId(raw);
    }

    public static PurchaseId unique() {
        return new PurchaseId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return raw;
    }
}
