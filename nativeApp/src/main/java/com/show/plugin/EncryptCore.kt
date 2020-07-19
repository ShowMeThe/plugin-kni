package com.show.plugin

import android.content.pm.PackageInfo
import android.content.pm.Signature


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