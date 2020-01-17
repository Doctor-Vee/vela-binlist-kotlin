package com.vela.binlistkotlin.repository

import com.vela.binlistkotlin.dto.CardCount
import com.vela.binlistkotlin.model.CardVerificationRecord
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CardVerificationRecordRepository : JpaRepository<CardVerificationRecord, Long> {
    @Query("SELECT new com.vela.binlistkotlin.dto.CardCount(record.cardNumber, count(record.cardNumber)) FROM CardVerificationRecord record GROUP BY record.cardNumber")
    fun getCardVerificationRecordByCardNumber(pageable: Pageable): Page<List<CardCount>>

    //@Query("select c from CardVerificationRecord c")
    //fun testQuery(): List<CardVerificationRecord>
}