package com.zilch.payments.domain.purchase;

import com.zilch.payments.domain.account.AccountId;

import java.util.Optional;
import java.util.Set;

public interface PurchaseRepository {

    Purchase save(Purchase purchase);

    Optional<Purchase> findById(PurchaseId purchaseId);

    Set<Purchase> findByAccountId(AccountId accountId);

}
