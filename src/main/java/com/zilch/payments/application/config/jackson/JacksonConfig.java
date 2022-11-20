package com.zilch.payments.application.config.jackson;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import com.zilch.payments.domain.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer.OFFSET_DATE_TIME;
import static com.zilch.payments.application.config.jackson.ReflectionUtils.getSubTypesOf;

@Slf4j
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer modulesSettingJackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            DateTimeFormatter zonedDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
            builder.serializers(
                    offsetDateTimeSerializer(zonedDateTimeFormatter),
                    new IdSerializer(Id.class)
            );
            builder.deserializers(offsetDateTimeDeserializer(zonedDateTimeFormatter));
            builder.deserializers(buildIdDeserializers());
        };
    }

    private OffsetDateTimeSerializer offsetDateTimeSerializer(DateTimeFormatter zonedDateTimeFormatter) {
        return new OffsetDateTimeSerializer(OffsetDateTimeSerializer.INSTANCE, null, zonedDateTimeFormatter) {
        };
    }

    private InstantDeserializer<OffsetDateTime> offsetDateTimeDeserializer(DateTimeFormatter zonedDateTimeFormatter) {
        return new InstantDeserializer<>(OFFSET_DATE_TIME, zonedDateTimeFormatter) {
        };
    }

    private JsonDeserializer<?>[] buildIdDeserializers() {
        Set<Class<? extends Id>> idClasses = getSubTypesOf(Id.class);
        return idClasses.stream().map(IdDeserializer::new).toList().toArray(new JsonDeserializer<?>[0]);
    }

}
