package com.vela.binlistkotlin.usecase.data.models

import lombok.Data

@Data
class Bank {
    var name: String? = null
    var url: String? = null
    var phone: String? = null
    var city: String? = null
}