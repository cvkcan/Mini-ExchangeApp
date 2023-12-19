package com.cvkcan.exchangeapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cvkcan.exchangeapp.model.Basket
import com.cvkcan.exchangeapp.model.UserInformation

@Database(entities = [Basket::class, UserInformation::class], version = 1)
abstract class GeneralDatabase : RoomDatabase() {

    abstract fun basketDao(): BasketDao
    abstract fun userInformationDao(): UserInformationDao

    companion object {
        @Volatile
        private var INSTANCE: GeneralDatabase? = null

        fun getInstance(context: Context): GeneralDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GeneralDatabase::class.java,
                    "ExchangeDb2"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
