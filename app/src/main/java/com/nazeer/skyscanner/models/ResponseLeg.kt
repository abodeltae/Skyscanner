package com.nazeer.skyscanner.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ResponseLeg(
    @SerializedName("Id") val id: String,
    @SerializedName("SegmentIds") val segmentIds: List<Int>,
    @SerializedName("OriginStation") val originStation: Int,
    @SerializedName("DestinationStation") val destinationStation: Int,
    @SerializedName("Stops") val stops: List<Int>,
    @SerializedName("Departure") val departureTime: Date,
    @SerializedName("Arrival") val arrivalTime: Date,
    @SerializedName("Duration") val durationMinutes: Int,
    @SerializedName("Carriers") val carriers : List<Int>
)