package com.vela.binlistkotlin.domain.service

import com.vela.binlistkotlin.domain.entities.CardDetail
import com.vela.binlistkotlin.usecase.data.models.CardCount
import com.vela.binlistkotlin.domain.entities.CardVerificationRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CardVerificationRecordRepository : JpaRepository<CardVerificationRecord, Long> {

    @Query("SELECT new com.vela.binlistkotlin.usecase.data.models.CardCount(record.cardNumber, count(record.cardNumber)) FROM CardVerificationRecord record GROUP BY record.cardNumber")
    fun getCardVerificationRecordByCardNumber(): List<CardCount>
}