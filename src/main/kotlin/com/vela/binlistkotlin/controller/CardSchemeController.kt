package com.vela.binlistkotlin.controller

import com.vela.binlistkotlin.dto.customer_response.CardStatisticsResponse
import com.vela.binlistkotlin.dto.customer_response.CardVerificationResponse
import com.vela.binlistkotlin.exception.InvalidPageException
import com.vela.binlistkotlin.service.CardSchemeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/card-scheme")
class CardSchemeController {
    @Autowired
    lateinit var cardSchemeService: CardSchemeService

    @GetMapping("/verify/{cardNumber}")
    fun verifyCard(@PathVariable cardNumber: String?): ResponseEntity<CardVerificationResponse> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity: HttpEntity<*> = HttpEntity<Any>(headers)
        return ResponseEntity(cardSchemeService.performCardVerification(cardNumber, entity), HttpStatus.OK)
    }

    @GetMapping(value = ["/stats"], params = ["start", "limit"])
    fun getCardStatistics(@RequestParam("start") start: Int,
                          @RequestParam("limit") limit: Int): ResponseEntity<CardStatisticsResponse?> {
        if (start < 0) throw InvalidPageException()
        return ResponseEntity(cardSchemeService.getCardVerificationRecords(start, limit), HttpStatus.OK)
    }
}