package com.zilch.payments.boundary.outbound.elastic;

import com.zilch.payments.domain.purchase.PurchaseView;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchPurchaseRepository extends ElasticsearchRepository<PurchaseView, String> {
}
