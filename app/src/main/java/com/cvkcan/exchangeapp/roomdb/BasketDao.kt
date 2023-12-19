package com.cvkcan.exchangeapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cvkcan.exchangeapp.model.Basket
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
@Dao
interface BasketDao {
    @Query("SELECT * FROM basket")
    fun getAllBaskets() : List<Basket>
    @Query("DELETE FROM basket WHERE id = :basketId")
    fun deleteBasketById(basketId: Int)
    @Insert
    fun insertBasket(vararg basket : Basket)
    @Update
    fun updateBasket(vararg basket: Basket)
//    @Delete
//    fun deleteBasket(vararg basket : Basket)


}