package com.event.sample.eventcalendar.sqs.consumer

import com.event.sample.eventcalendar.domain.EventData
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component
class SqsMessageListener(
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(SqsMessageListener::class.java)

    @SqsListener("\${spring.cloud.aws.sqs.queueName}")
    fun queueListener(message: Message<String>) {
        logger.info("Thread name: {}", Thread.currentThread().name)
        logger.info("Receive message: {}", message.payload)
        val eventData = objectMapper.readValue(message.payload, EventData::class.java)
        logger.info("Receive message: {}", eventData)
    }

}