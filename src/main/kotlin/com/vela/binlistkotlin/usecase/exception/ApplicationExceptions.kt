package com.vela.binlistkotlin.usecase.exception

class InvalidInputException (errorMessage: String) : RuntimeException (errorMessage)

class NetworkErrorException (errorMessage: String) : RuntimeException (errorMessage)

class OutOfBoundsException (errorMessage: String) : RuntimeException (errorMessage)