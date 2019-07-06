package com.nazeer.skyscanner.models

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("Symbol") val symbol: String,
    @SerializedName("Code") val code: String)