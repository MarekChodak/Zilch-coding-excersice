package com.zilch.payments.domain.purchase;

import lombok.Builder;

import java.math.BigDecimal;

public record PurchaseSummaryRecord(PurchaseId purchaseId,
                                    String itemName,
                                    BigDecimal price,
                                    BigDecimal paid) {


    @Builder
    public PurchaseSummaryRecord {
    }

    public static PurchaseSummaryRecord from(Purchase purchase) {
        return PurchaseSummaryRecord.builder()
                .purchaseId(purchase.getId())
                .itemName(purchase.getItemName())
                .price(purchase.getPrice())
                .paid(purchase.paidAmount())
                .build();

    }
}
