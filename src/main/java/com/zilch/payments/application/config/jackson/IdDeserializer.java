package com.zilch.payments.application.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.zilch.payments.domain.Id;
import lombok.SneakyThrows;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class IdDeserializer<T extends Id> extends StdDeserializer<Id> {

    private final Class<?> clazz;

    public IdDeserializer(Class<?> vc) {
        super(vc);
        this.clazz = vc;
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        var value = parser.getCodec().readValue(parser, String.class);
        return isNotBlank(value) ? instantiateFromString(value) : null;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private T instantiateFromString(String value) {
        var constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        return (T) constructor.newInstance(value);
    }
}
