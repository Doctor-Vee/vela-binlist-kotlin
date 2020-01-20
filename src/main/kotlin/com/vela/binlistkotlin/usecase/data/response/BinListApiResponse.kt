package com.vela.binlistkotlin.usecase.data.response

import com.vela.binlistkotlin.usecase.data.models.Bank
import com.vela.binlistkotlin.usecase.data.models.Country
import com.vela.binlistkotlin.usecase.data.models.Number

class BinListApiResponse {
    var number: Number? = null

    var scheme: String? = null

    var type: String? = null

    var brand: String? = null

    var prepaid: Boolean = false

    var country: Country? = null

    var bank: Bank? = null
}