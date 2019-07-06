package com.nazeer.skyscanner.models

import com.google.gson.annotations.SerializedName

data class Carrier(
    @SerializedName("Id")val id :Int,
    @SerializedName("Name")val name :String,
    @SerializedName("ImageUrl")val imageUrl :String,
    @SerializedName("DisplayCode")val displayCode :String)