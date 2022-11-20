package com.zilch.payments.boundary.outbound.mongo.purchase;

import com.zilch.payments.domain.purchase.Purchase;
import com.zilch.payments.domain.purchase.PurchaseId;
import com.zilch.payments.domain.purchase.PurchaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoPurchasesRepository extends MongoRepository<Purchase, PurchaseId>, PurchaseRepository {
    
}
