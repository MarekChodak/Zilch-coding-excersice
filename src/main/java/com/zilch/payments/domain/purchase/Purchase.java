package com.zilch.payments.domain.purchase;

import com.zilch.payments.domain.account.AccountId;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
@Document("purchases")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Purchase {

    @Id
    private PurchaseId id;

    private AccountId accountId;

    private BigDecimal price;

    private String itemName;

    private List<Payment> payments;

    @Version
    private Long version;

    public static Purchase from(NewPurchaseCommand newPurchaseCommand) {
        return Purchase.builder()
                .id(PurchaseId.unique())
                .accountId(newPurchaseCommand.accountId())
                .price(newPurchaseCommand.price())
                .itemName(newPurchaseCommand.itemName())
                .payments(new ArrayList<>())
                .build();
    }

    public void makePayment(PaymentCommand paymentCommand) {
        payments.add(new Payment(LocalDateTime.now(), paymentCommand.amount()));
    }

    public BigDecimal paidAmount() {
        return payments.stream()
                .map(Payment::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

