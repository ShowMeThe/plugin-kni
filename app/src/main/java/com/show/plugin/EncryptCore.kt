package com.show.plugin

import android.content.Context


class EncryptCore {


    init {
        System.loadLibrary("KSharelib")
        initCore()
    }
    companion object{
        private val ins by  lazy { EncryptCore() }
        fun  getInstant() = ins
    }

    external fun initCore()

    external fun getTest():String

    external fun get(text:String):String
}