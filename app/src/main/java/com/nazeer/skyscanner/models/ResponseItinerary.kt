package com.nazeer.skyscanner.models

import com.google.gson.annotations.SerializedName

data class ResponseItinerary(
    @SerializedName("OutboundLegId") val outboundLegId: String,
    @SerializedName("InboundLegId") val inboundLegId: String,
    @SerializedName("PricingOptions") val pricingOptions: List<PricingOption>
) {
    data class PricingOption(
        @SerializedName("Agents") val agents: List<Int>,
        @SerializedName("Price") val price: Double
    )
}