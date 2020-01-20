package com.vela.binlistkotlin.infrastructure.web.advice

import com.vela.binlistkotlin.usecase.data.response.ErrorResponse
import com.vela.binlistkotlin.usecase.exception.InvalidInputException
import com.vela.binlistkotlin.usecase.exception.NetworkErrorException
import com.vela.binlistkotlin.usecase.exception.OutOfBoundsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice(basePackages = ["com.vela.binlistkotlin.infrastructure.web.controllers"])
class ExceptionHandler {

    @ExceptionHandler(value = [InvalidInputException::class])
    fun invalidInputException(ex: InvalidInputException): ResponseEntity<*> {
        println("Hello world")
        val errorResponse = ErrorResponse()
        errorResponse.error = HttpStatus.BAD_REQUEST.toString()
        errorResponse.message = ex.message.toString()
        return ResponseEntity<Any>(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [OutOfBoundsException::class])
    fun invalidPageException(ex: OutOfBoundsException): ResponseEntity<*> {
        val errorResponse = ErrorResponse()
        errorResponse.error = HttpStatus.NOT_FOUND.toString()
        errorResponse.message = ex.message.toString()
        return ResponseEntity<Any>(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [NetworkErrorException::class])
    fun statusCodeException(ex: NetworkErrorException): ResponseEntity<*> {
        val errorResponse = ErrorResponse()
        errorResponse.error = HttpStatus.SERVICE_UNAVAILABLE.toString()
        errorResponse.message = ex.message.toString()
        return ResponseEntity<Any>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE)
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(ex: Exception): ResponseEntity<*> {
        val errorResponse = ErrorResponse()
        errorResponse.error = HttpStatus.INTERNAL_SERVER_ERROR.toString()
        errorResponse.message = ex.message.toString()
        return ResponseEntity<Any>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}