package com.zilch.payments.application.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventPublisher implements EventPublisher{

    private final ApplicationEventPublisher eventPublisher;

    public void publishPurchaseEvent(final PurchaseCreatedEvent event) {
        eventPublisher.publishEvent(event);
    }
}
