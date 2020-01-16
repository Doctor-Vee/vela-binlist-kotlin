package com.vela.binlistkotlin.repository

import com.vela.binlistkotlin.model.CardDetail
import org.springframework.data.jpa.repository.JpaRepository

interface CardDetailRepository : JpaRepository<CardDetail, String> {
    fun findByCardNumber(cardNumber: String) : CardDetail
}