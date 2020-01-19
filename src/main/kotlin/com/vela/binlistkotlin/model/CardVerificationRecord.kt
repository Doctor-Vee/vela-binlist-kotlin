package com.vela.binlistkotlin.model

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "card_verification_records")
class CardVerificationRecord (cardNumber: String){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "card_number")
    lateinit var cardNumber: String

    @CreationTimestamp
    @Column(name = "request_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    lateinit var requestDate: Date

}


