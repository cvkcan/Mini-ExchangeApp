package com.cvkcan.exchangeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class MainActivity : AppCompatActivity() {
//    val db by lazy { DBHelper(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        db.readBasketData()
        var dbim = DBHelper(this)
    }
}