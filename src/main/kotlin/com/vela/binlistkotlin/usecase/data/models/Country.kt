package com.vela.binlistkotlin.usecase.data.models

import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
class Country {
    var numeric: String? = null

    var alpha2: String? = null

    var name: String? = null

    var emoji: String? = null

    var currency: String? = null

    var latitude: Long = 0

    var longitude: Long = 0
}