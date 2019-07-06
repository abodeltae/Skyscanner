package com.nazeer.skyscanner.models

import java.util.*

data class ProcessedLeg(
    val departureTime: Date,
    val arrivalTime: Date,
    val durationMinutes: Int,
    val stops: Int,
    val departurePlace: Place,
    val arrivalPlace: Place,
    val carrier: Carrier
)

data class TripItem(
    val agent: Agent,
    val outBoundLeg: ProcessedLeg,
    val inBoundLeg: ProcessedLeg?,
    val price: Double,
    val priceCurrency: Currency
)
