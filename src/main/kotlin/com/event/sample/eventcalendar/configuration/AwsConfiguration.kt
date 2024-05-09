package com.event.sample.eventcalendar.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.operations.SqsTemplate
import java.net.URI
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient


@Configuration
class AwsConfiguration(
    @Value("\${spring.cloud.aws.sqs.region.static}") private val region: String,
    @Value("\${spring.cloud.aws.sqs.queueName}") private val queueName: String,
    @Value("\${spring.cloud.aws.sqs.endpoint}") private val endpoint: String
) {

    @Bean
    fun sqsAsyncClient(): SqsAsyncClient {
        return SqsAsyncClient.builder()
            .endpointOverride(URI(endpoint))
            .region(Region.of(region))
            .build()
    }

    @Bean
    fun sqsTemplate(sqsAsyncClient: SqsAsyncClient, objectMapper: ObjectMapper): SqsTemplate {
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient)
            .configureDefaultConverter {
                it.setObjectMapper(objectMapper)
            }
            .configure {
                it.defaultQueue(queueName)
            }.build()
    }

}