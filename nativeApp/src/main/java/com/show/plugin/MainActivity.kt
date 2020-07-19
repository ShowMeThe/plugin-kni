package com.show.plugin

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {
            val encode = EncryptCore.getInstant().encode("123132")
            val decode = EncryptCore.getInstant().decode(encode)
            tvText.text = encode
            tvText2.text = decode
        }catch (e:Exception){
            e.printStackTrace()
        }

    }



}