package com.show.plugin


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import javax.crypto.Cipher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            tvText.text = EncryptCore.getInstant().getPackageName()
        }catch (e:Exception){
            e.printStackTrace()
        }catch (e:NoSuchMethodError){
            e.printStackTrace()
        }





    }



}