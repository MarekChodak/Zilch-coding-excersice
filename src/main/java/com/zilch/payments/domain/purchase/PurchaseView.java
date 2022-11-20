package com.zilch.payments.domain.purchase;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@Getter
@Document(indexName = "purchases")
public class PurchaseView {

    @Id
    private String purchaseId;

    @Field(type = FieldType.Text)
    private String itemName;

    @Field(type = FieldType.Double)
    private Double price;

    public static PurchaseView from(Purchase purchase) {
        return PurchaseView.builder()
                .purchaseId(purchase.getId().raw())
                .itemName(purchase.getItemName())
                .price(purchase.getPrice().doubleValue())
                .build();
    }
}
