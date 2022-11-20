package com.zilch.payments.boundary.inbound.controllers.purchase;

import com.zilch.payments.application.services.purchase.PurchaseService;
import com.zilch.payments.domain.account.AccountId;
import com.zilch.payments.domain.purchase.PurchaseId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class PurchaseController {

    private final PurchaseService purchaseService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/account/{accountId}/purchases")
    PurchaseId makePurchase(@RequestBody @Valid @NotNull NewPurchaseDocument newPurchaseDocument,
                            @PathVariable AccountId accountId) {
        return purchaseService.makePurchase(newPurchaseDocument.toCommand(accountId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("purchases/{purchaseId}/payment")
    void makePayment(@RequestBody @Valid @NotNull PaymentDocument paymentDocument,
                     @PathVariable PurchaseId purchaseId) {
        purchaseService.makePayment(paymentDocument.toCommand(purchaseId));
    }
}
