package com.cvkcan.exchangeapp

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {
    @GET("embed/altin.json")
    fun getData(): Call<ApiResponse>
}