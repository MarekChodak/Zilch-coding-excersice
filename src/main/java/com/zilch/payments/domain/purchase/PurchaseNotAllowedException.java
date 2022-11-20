package com.zilch.payments.domain.purchase;

import com.zilch.payments.domain.account.AccountId;

public class PurchaseNotAllowedException extends RuntimeException {
    public PurchaseNotAllowedException(AccountId accountId) {
        super("Purchase not allowed for " + accountId.raw());
    }
}
