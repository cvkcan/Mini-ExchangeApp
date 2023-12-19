package com.cvkcan.exchangeapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.cvkcan.exchangeapp.model.Basket
import com.cvkcan.exchangeapp.roomdb.BasketDao
import com.cvkcan.exchangeapp.roomdb.GeneralDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var db: GeneralDatabase
    private lateinit var basketDao: BasketDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Room database and BasketDao
//        db = Room.databaseBuilder(
//            applicationContext, GeneralDatabase::class.java,
//            "ExchangeDb2"
//        )
//            .allowMainThreadQueries()
//            .build()
//
//        basketDao = db.basketDao()


    }
}
