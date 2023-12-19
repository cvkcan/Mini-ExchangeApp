package com.cvkcan.exchangeapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DB_NAME : String = "ExchangeApp"
class DBHelper
    (val context : Context)
    : SQLiteOpenHelper(context, DB_NAME,null,1) {

    private val TABLE_NAME = "UserInformation"
    private val TABLE_USED_ID = "Id"
    private val COL_BALANCE = "Balance"
    private val COL_GOLD_QUANTITY = "GoldQuantity"
    private val COL_EUR_QUANTITY = "EurQuantity"
    private val COl_USD_QUANTITY = "UsdQuantity"

    private val TABLE_NAME2 = "Basket"
    private val TABLE_USED_ID2 = "Id"
    private val COL_CURRENCIE_TYPES = "CurrencieTypes"
    private val COL_PER_QUANTITY = "PerQuantity"
    override fun onCreate(db: SQLiteDatabase?) {
        var createUserInformationTable  = "CREATE TABLE " + TABLE_NAME +"("+
                TABLE_USED_ID + " INTEGER PRIMARY KEY," +
                COL_BALANCE + " INTEGER," +
                COL_EUR_QUANTITY + " INTEGER," +
                COL_GOLD_QUANTITY + " INTEGER," +
                COl_USD_QUANTITY + " INTEGER)"

        val  createBasketTable = "CREATE TABLE " + TABLE_NAME2 +"("+
                TABLE_USED_ID2 + " INTEGER PRIMARY KEY," +
                COL_CURRENCIE_TYPES + " VARCHAR(256)," +
                COL_PER_QUANTITY + " INTEGER)"

        db?.execSQL(createBasketTable)
        db?.execSQL(createUserInformationTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertBasket(basket : BasketModel){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_PER_QUANTITY, basket.PerQuantity)
        cv.put(COL_CURRENCIE_TYPES,basket.CurrencieTypes)
        cv.put(TABLE_USED_ID2,basket.Id)
        var result = db.insert(TABLE_NAME2,null,cv)
        if (result == (-1).toLong()){
            Toast.makeText(context,"Basket Done", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(context,"Basket Failed",Toast.LENGTH_LONG).show()
        }
    }

    fun insertUserInformation(userInformation : UserInformationModel){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(TABLE_USED_ID,userInformation.Id)
        cv.put(COL_BALANCE,userInformation.Balance)
        cv.put(COL_EUR_QUANTITY,userInformation.EurQuantity)
        cv.put(COl_USD_QUANTITY,userInformation.UsdQuantity)
        cv.put(COL_GOLD_QUANTITY,userInformation.GoldQuantity)
        var result = db.insert(TABLE_NAME,null,cv)
        if (result == (-1).toLong()){
            Toast.makeText(context,"User Done!",Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(context,"User Failed!",Toast.LENGTH_LONG).show()
    }

    fun updateUserInformation(userInformation: UserInformationModel){

    }
    fun updateBasket(basket : BasketModel){
        val db = this.writableDatabase
        val cv = ContentValues()
    }
    fun readBasketData():MutableList<BasketModel>{
        val userList = mutableListOf<BasketModel>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val user = BasketModel(result.getColumnIndex(TABLE_USED_ID2).toInt(),
                    result.getColumnIndex(COL_CURRENCIE_TYPES).toString(),
                    result.getColumnIndex(COL_PER_QUANTITY).toInt())
                userList.add(user)
                println("OLDU GALIBA")
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return userList
    }
}


//private val TABLE_NAME2 = "Basket"
//private val TABLE_USED_ID2 = "Id"
//private val COL_CURRENCIE_TYPES = "CurrencieTypes"
//private val COL_PER_QUANTITY = "PerQuantity"
