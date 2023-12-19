package com.cvkcan.exchangeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Basket(
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "CurrencieTypes")
    val CurrencieTypes: String,
    @ColumnInfo(name = "PerQuantity")
    val PerQuantity: Int
)