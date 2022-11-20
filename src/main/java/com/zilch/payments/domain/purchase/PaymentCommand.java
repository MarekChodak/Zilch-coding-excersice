package com.zilch.payments.domain.purchase;

import lombok.Builder;

import java.math.BigDecimal;

public record PaymentCommand(
        BigDecimal amount,
        PurchaseId purchaseId
) {

    @Builder
    public PaymentCommand {
    }
}
