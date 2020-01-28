package com.vela.binlistkotlin.infrastructure.web.controllers

import com.vela.binlistkotlin.usecase.data.response.CardStatisticsResponse
import com.vela.binlistkotlin.usecase.data.response.CardVerificationResponse
import com.vela.binlistkotlin.usecase.exception.InvalidInputException

import com.vela.binlistkotlin.usecase.service.CardSchemeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import javax.management.Query

@RestController
@RequestMapping("/card-scheme")
class CardSchemeController {
    @Autowired
    lateinit var cardSchemeService: CardSchemeService

    @GetMapping("/search/{searchQuery}")
    fun searchCard(@PathVariable searchQuery: String): ResponseEntity<List<CardVerificationResponse>> {
        return ResponseEntity(cardSchemeService.searchForCard(searchQuery), HttpStatus.OK)
    }

    @GetMapping("/verify/{cardNumber}")
    fun verifyCard(@PathVariable cardNumber: String): ResponseEntity<CardVerificationResponse> {
        return ResponseEntity(cardSchemeService.performCardVerification(cardNumber), HttpStatus.OK)
    }

    @GetMapping(value = ["/stats"], params = ["start", "limit"])
    fun getCardStatistics(@RequestParam("start") start: Int,
                          @RequestParam("limit") limit: Int): ResponseEntity<CardStatisticsResponse?> {
        return ResponseEntity(cardSchemeService.getCardVerificationRecords(start, limit), HttpStatus.OK)
    }
}