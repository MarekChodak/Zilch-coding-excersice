package com.zilch.payments.boundary.inbound.controllers;

public record ErrorDocument(String message) {
    public static ErrorDocument of(String message) {
        return new ErrorDocument(message);
    }
}
