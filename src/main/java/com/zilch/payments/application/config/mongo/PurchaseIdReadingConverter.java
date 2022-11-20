package com.zilch.payments.application.config.mongo;

import com.zilch.payments.domain.purchase.PurchaseId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class PurchaseIdReadingConverter implements Converter<String, PurchaseId> {

    @Override
    public PurchaseId convert(@Nullable String source) {
        return isNotBlank(source) ? new PurchaseId(source) : null;
    }
}
