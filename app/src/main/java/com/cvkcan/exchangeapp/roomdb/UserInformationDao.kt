package com.cvkcan.exchangeapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cvkcan.exchangeapp.model.UserInformation
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface UserInformationDao {
    @Query("SELECT * FROM userinformation")
    fun getAllUserInformation () : List<UserInformation>
    @Query("DELETE FROM userinformation WHERE id = :userId")
    fun deleteUserInformationById (userId : Int)
    @Insert
    fun insertUserInformation (userInformation: UserInformation)
    @Update
    fun updateUserInformation (userInformation: UserInformation)
//    @Delete
//    fun deleteUserInformation (userInformation: UserInformation)

}