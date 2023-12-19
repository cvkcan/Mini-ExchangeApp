package com.cvkcan.exchangeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInformation (
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "Balance")
    val Balance : Int,
    @ColumnInfo(name = "GoldQuantity")
    val GoldQuantity : Int,
    @ColumnInfo(name = "EurQuantity")
    val EurQuantity: Int,
    @ColumnInfo(name = "UsdQuantity")
    val UsdQuantity: Int
)
