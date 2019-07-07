package com.nazeer.skyscanner.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ResponseQuery(
    @SerializedName("CabinClass") val cabinClass: String,
    @SerializedName("Currency") val currency: String,
    @SerializedName("OutboundDate") val outboundDate: Date,
    @SerializedName("InboundDate") val inboundDate: Date,
    @SerializedName("OriginPlace") val originPlace: String,
    @SerializedName("DestinationPlace") val destinationPlace: String,
    @SerializedName("Adults") val adults: Int
)