package com.nazeer.skyscanner.models

import com.google.gson.annotations.SerializedName

data class Place (
    @SerializedName("Id")val id :Int,
    @SerializedName("Name")val name :String,
    @SerializedName("Code")val code :String
)