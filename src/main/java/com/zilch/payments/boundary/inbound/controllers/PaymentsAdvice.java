package com.zilch.payments.boundary.inbound.controllers;

import com.zilch.payments.domain.account.AccountNotFoundException;
import com.zilch.payments.domain.purchase.PurchaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PaymentsAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    ErrorDocument handleAccountNotFound(AccountNotFoundException ex) {
        return ErrorDocument.of(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PurchaseNotFoundException.class)
    ErrorDocument handlePurchaseNotFound(PurchaseNotFoundException ex) {
        return ErrorDocument.of(ex.getMessage());
    }

}
