package com.vela.binlistkotlin.domain.service


import com.vela.binlistkotlin.domain.entities.CardDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardDetailRepository : JpaRepository<CardDetail, String> {
    fun findByCardNumber(cardNumber: String?): CardDetail?
}