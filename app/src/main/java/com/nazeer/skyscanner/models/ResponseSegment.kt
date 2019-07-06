package com.nazeer.skyscanner.models

import com.google.gson.annotations.SerializedName
import java.util.*


data class ResponseSegment(
    @SerializedName("Id") val id: Int,
    @SerializedName("OriginStation") val originStation: Int,
    @SerializedName("DepartureDateTime") val departureDateTime: Date,
    @SerializedName("ArrivalDateTime") val arrivalDateTime: Date,
    @SerializedName("Carrier") val carrier: Int,
    @SerializedName("Duration") val durationMinutes: Int,
    @SerializedName("FlightNumber") val flightNumber: String
)