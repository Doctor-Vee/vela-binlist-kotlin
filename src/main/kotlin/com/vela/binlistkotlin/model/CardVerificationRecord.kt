package com.vela.binlistkotlin.model

import lombok.Data
import java.util.*
import javax.persistence.*

@Data
@Entity(name = "card_verification_records")
class CardVerificationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "card_number")
    var cardNumber: String? = null

    @Column(name = "request_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    var requestDate: Date? = null

    constructor(cardNumber: String){
        this.cardNumber = cardNumber
    }


}


