package com.vela.binlistkotlin.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfiguration {
    @Bean
    fun getRestTemplate(): RestTemplate? {
        return RestTemplate()
    }
}