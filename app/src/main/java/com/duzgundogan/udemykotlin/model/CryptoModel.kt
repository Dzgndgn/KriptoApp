package com.duzgundogan.udemykotlin.model

import com.google.gson.annotations.SerializedName

/*data class CryptoModel(

    @SerializedName("id")
    val currency :String,
    @SerializedName("symbol")
    val symbol :String,
    @SerializedName("priceUsd")
    val price: String
)*/
data class CryptoModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("quote")
    val quote: Quote
)

data class Quote(
    @SerializedName("USD")
    val usd: USD
)

data class USD(
    @SerializedName("price")
    val price: Double
)
