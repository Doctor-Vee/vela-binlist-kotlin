package com.vela.binlistkotlin.model

import com.vela.binlistkotlin.utils.CardType
import javax.persistence.*


@Entity
@Table(name = "card_details")
class CardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "card_number")
    var cardNumber: String? = null

    var scheme: String? = null

    @Enumerated(EnumType.STRING)
    var type: CardType? = null

    var bank: String? = null

}