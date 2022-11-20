package com.zilch.payments.application.events;

public interface EventPublisher {

    void publishPurchaseEvent(final PurchaseCreatedEvent event);

}
