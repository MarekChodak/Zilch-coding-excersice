package com.zilch.payments.boundary.inbound.controllers.purchase;

import com.zilch.payments.domain.purchase.PaymentCommand;
import com.zilch.payments.domain.purchase.PurchaseId;

import java.math.BigDecimal;

public record PaymentDocument(
        BigDecimal amount
) {
    public PaymentCommand toCommand(PurchaseId purchaseId) {
        return PaymentCommand.builder()
                .amount(amount)
                .purchaseId(purchaseId)
                .build();
    }
}
