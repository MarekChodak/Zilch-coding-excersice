package com.zilch.payments.boundary.inbound.controllers.purchase;

import com.zilch.payments.domain.account.AccountId;
import com.zilch.payments.domain.purchase.NewPurchaseCommand;
import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record NewPurchaseDocument(
        @Min(1)
        BigDecimal price,
        @NotBlank
        String itemName
) {

    @Builder
    public NewPurchaseDocument {
    }

    public NewPurchaseCommand toCommand(AccountId accountId) {
        return NewPurchaseCommand.builder()
                .accountId(accountId)
                .price(price)
                .itemName(itemName)
                .build();
    }
}
