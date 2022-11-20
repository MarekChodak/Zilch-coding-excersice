package com.zilch.payments.application.services;

import com.zilch.payments.domain.account.AccountId;
import com.zilch.payments.domain.purchase.Purchase;
import com.zilch.payments.domain.purchase.PurchaseId;
import com.zilch.payments.domain.purchase.PurchaseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryPurchaseRepository implements PurchaseRepository {

    private final Map<PurchaseId, Purchase> db = new HashMap<>();

    @Override
    public Purchase save(Purchase purchase) {
        return db.put(purchase.getId(), purchase);
    }

    @Override
    public Optional<Purchase> findById(PurchaseId purchaseId) {
        return Optional.ofNullable(db.get(purchaseId));
    }

    @Override
    public Set<Purchase> findByAccountId(AccountId accountId) {
        return db.values().stream()
                .filter(purchase -> purchase.getAccountId().equals(accountId))
                .collect(Collectors.toSet());
    }
}
