package com.vela.binlistkotlin.usecase.exception

class InvalidPageException : RuntimeException{
    constructor(){}
    constructor(message: String?) : super(message){

    }
}