package com.nazeer.skyscanner.models

import java.util.*

data class ProcessedQuery(
    val originPlace: Place,
    val destinationPlace: Place,
    val outboundTime: Date,
    val inboundTime: Date,
    val cabinClass: String,
    val adults: Int
)