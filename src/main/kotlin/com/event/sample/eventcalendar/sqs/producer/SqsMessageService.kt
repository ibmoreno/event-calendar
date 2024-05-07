package com.event.sample.eventcalendar.sqs.producer

import com.event.sample.eventcalendar.domain.EventData
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.operations.SendResult
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

interface SqsMessageService {
    fun sendMessage(eventData: EventData)
}

@Service
class SqsMessageServiceImpl(
    private val sqsTemplate: SqsTemplate,
    private val objectMapper: ObjectMapper
) : SqsMessageService {
    private val logger = LoggerFactory.getLogger(SqsMessageServiceImpl::class.java)
    override fun sendMessage(eventData: EventData) {
        val json = objectMapper.writeValueAsString(eventData)
        val result: SendResult<String> = sqsTemplate.send(json)
        logger.info("Send result: {}", result)
    }
}