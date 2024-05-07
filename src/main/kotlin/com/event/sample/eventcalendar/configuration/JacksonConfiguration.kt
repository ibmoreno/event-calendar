package com.event.sample.eventcalendar.configuration

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.format.DateTimeFormatter
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JacksonConfiguration : Jackson2ObjectMapperBuilderCustomizer {

    override fun customize(jacksonObjectMapperBuilder: Jackson2ObjectMapperBuilder?) {
        jacksonObjectMapperBuilder
            ?.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            ?.deserializers(LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))
            ?.deserializers(LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE))
            ?.serializers(LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME))
            ?.serializers(LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE))
    }
}