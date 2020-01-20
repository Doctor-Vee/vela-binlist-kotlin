package com.vela.binlistkotlin.domain.entities

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "card_verification_records")
class CardVerificationRecord() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "card_number", nullable = false)
    lateinit var cardNumber: String

    @CreationTimestamp
    @Column(name = "request_date")
    lateinit var requestDate: Date

    constructor(cardNumber: String): this(){
        this.cardNumber = cardNumber
    }

}


