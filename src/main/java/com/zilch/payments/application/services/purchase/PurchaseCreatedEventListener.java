package com.zilch.payments.application.services.purchase;

import com.zilch.payments.application.events.PurchaseCreatedEvent;
import com.zilch.payments.boundary.outbound.elastic.ElasticsearchPurchaseRepository;
import com.zilch.payments.domain.purchase.PurchaseView;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseCreatedEventListener {

    private final ElasticsearchPurchaseRepository purchaseRepository;

    @Async
    @EventListener
    public void handle(PurchaseCreatedEvent event) {
        PurchaseView view = PurchaseView.from(event.purchase());
        purchaseRepository.save(view);
    }
}
