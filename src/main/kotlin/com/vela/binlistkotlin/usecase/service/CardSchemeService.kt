package com.vela.binlistkotlin.usecase.service

import com.vela.binlistkotlin.usecase.data.response.CardStatisticsResponse
import com.vela.binlistkotlin.usecase.data.response.CardVerificationResponse
import com.vela.binlistkotlin.usecase.exception.InvalidInputException
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException

@Service
interface CardSchemeService {
    @Throws(HttpStatusCodeException::class, InvalidInputException::class)
    fun performCardVerification(cardNumber: String?, entity: HttpEntity<*>?): CardVerificationResponse?

    @Throws(RuntimeException::class)
    fun getCardVerificationRecords(start: Int, limit: Int): CardStatisticsResponse?
}