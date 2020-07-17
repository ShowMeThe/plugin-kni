package com.show.plugin


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val code = EncryptCore.getInstant().encode("oidfjasjoi2que2haknsdJdakdpoew")
        val code2 = EncryptCore.getInstant().decode(code)

        tvText.text = code
        tvText2.text = code2

    }



}