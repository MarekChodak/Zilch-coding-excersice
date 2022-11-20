package com.zilch.payments.application.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zilch.payments.domain.Id;

import java.io.IOException;

public final class IdSerializer extends StdSerializer<Id> {

    public IdSerializer(Class<Id> t) {
        super(t);
    }

    @Override
    public void serialize(Id id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (id != null) {
            jsonGenerator.writeString(id.raw());
        }
    }

}
