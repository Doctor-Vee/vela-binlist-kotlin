package com.vela.binlistkotlin.service

import com.vela.binlistkotlin.dto.customer_response.CardStatisticsResponse
import com.vela.binlistkotlin.dto.customer_response.CardVerificationResponse
import com.vela.binlistkotlin.exception.InvalidInputException
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException

@Service
interface CardSchemeService {
    @Throws(HttpStatusCodeException::class, InvalidInputException::class)
    fun performCardVerification(cardNumber: String?, entity: HttpEntity<*>?): CardVerificationResponse?

    @Throws(RuntimeException::class)
    fun getCardVerificationRecords(pageable: Pageable?): CardStatisticsResponse?
}