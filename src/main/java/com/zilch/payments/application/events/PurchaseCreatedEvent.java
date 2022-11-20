package com.zilch.payments.application.events;

import com.zilch.payments.domain.purchase.Purchase;

public record PurchaseCreatedEvent(Purchase purchase) {

    public static PurchaseCreatedEvent from(Purchase purchase) {
        return new PurchaseCreatedEvent(purchase);
    }
}
