package com.vela.binlistkotlin.exception

class InvalidInputException : RuntimeException {
    constructor() {}
    constructor(message: String?) : super(message) {}
}