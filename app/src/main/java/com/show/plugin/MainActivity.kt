package com.show.plugin


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            tvText.text = EncryptCore.getInstant().getTest()
            tvText2.text =   EncryptCore.getInstant().get(EncryptCore.getInstant().getTest())
        }catch (e:Exception){
            e.printStackTrace()
        }catch (e:NoSuchMethodError){
            e.printStackTrace()
        }





    }



}