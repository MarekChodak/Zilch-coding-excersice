package com.zilch.payments.application.services;

import com.zilch.payments.application.events.EventPublisher;
import com.zilch.payments.application.events.PurchaseCreatedEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InMemoryEventPublisher implements EventPublisher {

    private final List<PurchaseCreatedEvent> publishedEvents = new ArrayList<>();

    @Override
    public void publishPurchaseEvent(PurchaseCreatedEvent event) {
        publishedEvents.add(event);
    }

}
