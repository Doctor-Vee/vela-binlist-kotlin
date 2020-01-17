package com.vela.binlistkotlin.exception

import com.vela.binlistkotlin.dto.customer_response.CardVerificationResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    var logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)


    @ExceptionHandler(value = [InvalidInputException::class])
    fun invalidInputException(ex: InvalidInputException, request: WebRequest): ResponseEntity<*>?{
        logger.error(ex.message)
        var cardVerificationResponse: CardVerificationResponse = CardVerificationResponse()
        cardVerificationResponse.success = false
        cardVerificationResponse.payload = null
        return ResponseEntity<Any>(cardVerificationResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [InvalidPageException::class])
    fun invalidPageException(ex: InvalidPageException, request: WebRequest): ResponseEntity<*>?{
        logger.error(ex.message)
        var cardVerificationResponse: CardVerificationResponse = CardVerificationResponse()
        cardVerificationResponse.success = false
        cardVerificationResponse.payload = null
        return ResponseEntity<Any>(cardVerificationResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [org.springframework.web.client.HttpStatusCodeException::class])
    fun statusCodeException(ex: HttpStatusCodeException, request: WebRequest) : ResponseEntity<*>?{
        logger.error(ex.message)
        var cardVerificationResponse: CardVerificationResponse = CardVerificationResponse()
        cardVerificationResponse.success = false
        cardVerificationResponse.payload = null
        return ResponseEntity<Any>(cardVerificationResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(ex: Exception, request: WebRequest?): ResponseEntity<*>? {
        logger.error(ex.message)
        var cardVerificationResponse: CardVerificationResponse = CardVerificationResponse()
        cardVerificationResponse    .success = false
        cardVerificationResponse.payload = null
        return ResponseEntity<Any>(cardVerificationResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}