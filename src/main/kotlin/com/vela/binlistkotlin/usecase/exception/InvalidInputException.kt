package com.vela.binlistkotlin.usecase.exception

class InvalidInputException : RuntimeException {
    constructor() {}
    constructor(message: String?) : super(message) {}
}