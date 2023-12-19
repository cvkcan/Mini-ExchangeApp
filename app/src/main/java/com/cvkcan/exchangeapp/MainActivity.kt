package com.cvkcan.exchangeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class MainActivity : AppCompatActivity() {
    val db by lazy { DBHelper(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var datas = BasketModel(2,"USD",3)
        var datas2 = UserInformationModel(2,1000,2,5,9)

        db.insertBasket(datas)
        db.insertUserInformation(datas2)



    }
}