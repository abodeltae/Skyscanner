package com.nazeer.skyscanner.models

import com.google.gson.annotations.SerializedName

data class FlightsResponse(
    @SerializedName("Query") val query: ResponseQuery,
    @SerializedName("Itineraries") val itineraries: List<ResponseItinerary>,
    @SerializedName("Legs") val legs: List<ResponseLeg>,
    @SerializedName("Segments") val segments: List<ResponseSegment>,
    @SerializedName("Carriers") val carriers: List<Carrier>,
    @SerializedName("Agents") val agents: List<Agent>,
    @SerializedName("Places") val places: List<Place>,
    @SerializedName ("Currencies") val currencies: List<Currency>
)