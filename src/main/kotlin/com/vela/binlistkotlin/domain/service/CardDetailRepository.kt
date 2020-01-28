package com.vela.binlistkotlin.domain.service


import com.vela.binlistkotlin.domain.entities.CardDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CardDetailRepository : JpaRepository<CardDetail, String> {
    @Query("SELECT card FROM CardDetail card WHERE lower(card.bank) LIKE LOWER(CONCAT('%', :searchQuery, '%')) " +
            "OR LOWER(card.scheme) LIKE LOWER(CONCAT('%', :searchQuery, '%')) " +
            "OR LOWER(card.type) LIKE LOWER(CONCAT('%', :searchQuery, '%')) ORDER BY card.id")
    fun searchCardDetails(searchQuery: String): List<CardDetail>

    fun findByCardNumber(cardNumber: String?): CardDetail?
}