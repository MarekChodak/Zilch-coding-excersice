package com.zilch.payments.domain.purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Payment(
        LocalDateTime madeAt,
        BigDecimal amount
) {
}
