package com.cvkcan.exchangeapp

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("USD")
    val usd : CurrencyModel,
    @SerializedName("EUR")
    val euro : CurrencyModel,
    @SerializedName("GA")
    val gold  : CurrencyModel
) {
}