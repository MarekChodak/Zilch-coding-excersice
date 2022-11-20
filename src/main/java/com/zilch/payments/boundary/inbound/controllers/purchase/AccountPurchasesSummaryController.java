package com.zilch.payments.boundary.inbound.controllers.purchase;

import com.zilch.payments.application.services.purchase.AccountPurchasesSummaryService;
import com.zilch.payments.domain.account.AccountId;
import com.zilch.payments.domain.purchase.PurchaseSummaryRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/account", produces = APPLICATION_JSON_VALUE)
public class AccountPurchasesSummaryController {

    private final AccountPurchasesSummaryService summaryService;

    @GetMapping("/{accountId}/purchases")
    List<PurchaseSummaryRecord> purchasesSummary(@PathVariable AccountId accountId) {
        return summaryService.forAccount(accountId);
    }

}
