package com.vela.binlistkotlin.model

import com.vela.binlistkotlin.utils.CardType
import lombok.Data
import java.util.*
import javax.persistence.*

@Data
@Entity(name = "card_details")
class CardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "card_number")
    var cardNumber: String? = null

    @Column(name = "card_number_length")
    var cardNumberLength: Int? = null

    var luhn: Boolean? = null

    var scheme: String? = null

    var brand: String? = null

    @Enumerated(EnumType.STRING)
    var type: CardType? = null

    var country: String? = null

    var bank: String? = null

}