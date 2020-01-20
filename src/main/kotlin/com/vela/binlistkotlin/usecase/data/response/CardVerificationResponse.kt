package com.vela.binlistkotlin.usecase.data.response

import com.vela.binlistkotlin.usecase.data.models.CardVerificationPayload

class CardVerificationResponse {
    var success: Boolean = false
    var payload: CardVerificationPayload? = null
}