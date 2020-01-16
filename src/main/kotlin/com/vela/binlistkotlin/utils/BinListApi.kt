package com.vela.binlistkotlin.utils

import lombok.Data
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service

@Data
@Service
@ConfigurationProperties(prefix = "binlist")
class BinListApi (){
    var url: String? = null
}