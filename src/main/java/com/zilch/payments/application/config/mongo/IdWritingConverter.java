package com.zilch.payments.application.config.mongo;

import com.zilch.payments.domain.Id;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.Nullable;

import static java.util.Objects.nonNull;

@WritingConverter
public class IdWritingConverter implements Converter<Id, String> {

    @Override
    public String convert(@Nullable Id id) {
        return nonNull(id) ? id.raw() : null;
    }
}
