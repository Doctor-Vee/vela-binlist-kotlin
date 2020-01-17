package com.vela.binlistkotlin.exception

class InvalidPageException : RuntimeException{
    constructor(){}
    constructor(message: String?) : super(message){

    }
}