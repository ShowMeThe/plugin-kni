package com.show.plugin


class EncryptCore {

    init {
        System.loadLibrary("KSharelib")
        initCore()
    }
    companion object{
        private val ins = EncryptCore()
        fun  getInstant() = ins
    }

    external fun initCore()

    external fun encode(text:String):String

    external fun decode(text:String):String
}