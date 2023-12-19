package com.cvkcan.exchangeapp.apis

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {
    @GET("embed/altin.json")
    fun getData(): Call<ApiResponse>
}