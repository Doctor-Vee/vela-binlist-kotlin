package com.vela.binlistkotlin.repository

import com.vela.binlistkotlin.model.CardVerificationRecord
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.awt.print.Pageable

@Repository
interface CardVerificationRecordRepository : JpaRepository<CardVerificationRecord, Long> {
    @Query("SELECT record.cardNumber AS cardNumber, COUNT(record.cardNumber) AS count FROM CardVerificationRecord record GROUP BY record.cardNumber")
    fun getCardVerificationRecordByCardNumber(pageable: Pageable) : Page<Map<String, Any>>
}