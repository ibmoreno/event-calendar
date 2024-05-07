package com.event.sample.eventcalendar.controller

import com.event.sample.eventcalendar.domain.EventData
import com.event.sample.eventcalendar.sqs.producer.SqsMessageService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/event")
class EventController(private val sqsMessageService: SqsMessageService) {

    private val logger = LoggerFactory.getLogger(EventController::class.java)

    @PostMapping("/add")
    fun salve(@RequestBody event: EventData): ResponseEntity<Any> {
        logger.info("Thread name: {}", Thread.currentThread().name)
        logger.info("Send message: {}", event)
        sqsMessageService.sendMessage(event)
        return ResponseEntity.ok().build()
    }

}