package com.zilch.payments.domain.purchase;

import com.zilch.payments.domain.account.AccountId;
import lombok.Builder;

import java.math.BigDecimal;

public record NewPurchaseCommand(
        AccountId accountId,
        BigDecimal price,
        String itemName
) {

    @Builder
    public NewPurchaseCommand {
    }
}
