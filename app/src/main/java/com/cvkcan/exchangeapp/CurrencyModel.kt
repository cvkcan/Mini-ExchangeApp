package com.cvkcan.exchangeapp

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("satis")
    val forexBuying : String,
    @SerializedName("alis")
    val forexSelling : String,
    @SerializedName("degisim")
    val variation : String,
    @SerializedName("d_oran")
    val exchangeRatio : String,
    @SerializedName("d_yon")
    val exchangeRatioDirection : String
    ) {
}