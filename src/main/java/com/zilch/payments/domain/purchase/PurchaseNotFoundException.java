package com.zilch.payments.domain.purchase;

import com.zilch.payments.domain.purchase.PurchaseId;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(PurchaseId purchaseId) {
        super("Could not find and purchase with id " + purchaseId.raw());
    }
}
