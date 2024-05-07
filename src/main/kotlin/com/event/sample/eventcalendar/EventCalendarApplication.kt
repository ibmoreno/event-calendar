package com.event.sample.eventcalendar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventCalendarApplication

fun main(args: Array<String>) {
    runApplication<EventCalendarApplication>(*args)
}
