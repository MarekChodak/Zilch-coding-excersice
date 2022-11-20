package com.zilch.payments.application.services.purchase;

import com.zilch.payments.domain.purchase.PurchaseSummaryRecord;
import com.zilch.payments.domain.account.AccountId;
import com.zilch.payments.domain.purchase.Purchase;
import com.zilch.payments.domain.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountPurchasesSummaryService {

    private final PurchaseRepository purchaseRepository;

    public List<PurchaseSummaryRecord> forAccount(AccountId accountId) {
        Set<Purchase> purchases = purchaseRepository.findByAccountId(accountId);
        return purchases.stream()
                .map(PurchaseSummaryRecord::from)
                .toList();
    }
}
