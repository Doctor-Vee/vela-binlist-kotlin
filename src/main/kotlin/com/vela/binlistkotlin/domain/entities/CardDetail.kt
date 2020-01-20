package com.vela.binlistkotlin.domain.entities

import com.vela.binlistkotlin.domain.enums.CardType
import javax.persistence.*


@Entity
@Table(name = "card_details")
class CardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "card_number", nullable = false)
    lateinit var cardNumber: String

    var scheme: String? = null

    @Enumerated(EnumType.STRING)
    var type: CardType? = null

    var bank: String? = null

}