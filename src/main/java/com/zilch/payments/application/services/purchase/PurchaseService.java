package com.zilch.payments.application.services.purchase;

import com.zilch.payments.application.events.EventPublisher;
import com.zilch.payments.application.events.PurchaseCreatedEvent;
import com.zilch.payments.domain.account.Account;
import com.zilch.payments.domain.account.AccountNotFoundException;
import com.zilch.payments.domain.account.AccountRepository;
import com.zilch.payments.domain.purchase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final AccountRepository accountRepository;

    private final PurchaseRepository purchaseRepository;

    private final EventPublisher eventPublisher;

    public PurchaseId makePurchase(NewPurchaseCommand newPurchaseCommand) {
        Optional<Account> possibleAccount = accountRepository.findById(newPurchaseCommand.accountId());
        Account account = possibleAccount.orElseThrow(() -> new AccountNotFoundException(newPurchaseCommand.accountId()));
        if (!account.isPurchaseAllowed()) {
            throw new PurchaseNotAllowedException(account.getId());
        }
        Purchase purchase = Purchase.from(newPurchaseCommand);
        purchaseRepository.save(purchase);
        eventPublisher.publishPurchaseEvent(PurchaseCreatedEvent.from(purchase));
        return purchase.getId();
    }

    public void makePayment(PaymentCommand paymentCommand) {
        Optional<Purchase> possiblePurchase = purchaseRepository.findById(paymentCommand.purchaseId());
        Purchase purchase = possiblePurchase.orElseThrow(() -> new PurchaseNotFoundException(paymentCommand.purchaseId()));
        purchase.makePayment(paymentCommand);
        purchaseRepository.save(purchase);
    }
}
