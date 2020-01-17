package com.vela.binlistkotlin.model

import lombok.Data
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "card_verification_records")
class CardVerificationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    @Column(name = "card_number", nullable = false)
    lateinit var cardNumber: String

    @Column(name = "request_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    var requestDate: Date? = null


}


