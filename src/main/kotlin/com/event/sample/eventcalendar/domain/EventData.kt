package com.event.sample.eventcalendar.domain

import java.time.LocalDateTime

data class EventData(
    val title: String,
    val text: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime? = null,
    val link: String? = null,
    val location: String
)
