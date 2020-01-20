package com.vela.binlistkotlin.usecase.service

import com.vela.binlistkotlin.usecase.data.response.CardStatisticsResponse
import com.vela.binlistkotlin.usecase.data.response.CardVerificationResponse
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Service

@Service
interface CardSchemeService {


    fun performCardVerification(cardNumber: String?): CardVerificationResponse?

    fun getCardVerificationRecords(start: Int, limit: Int): CardStatisticsResponse?
}