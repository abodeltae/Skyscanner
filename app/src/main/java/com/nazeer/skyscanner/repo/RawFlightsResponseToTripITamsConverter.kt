package com.nazeer.skyscanner.repo

import android.util.SparseArray
import com.nazeer.skyscanner.models.*

class RawFlightsResponseToTripITamsConverter {

    val agents = SparseArray<Agent>()
    val places = SparseArray<Place>()
    val carriers = SparseArray<Carrier>()
    val segments = SparseArray<ResponseSegment>()
    val legs = HashMap<String, ProcessedLeg>()
    val currencies = HashMap<String,Currency>()
    lateinit var selectedCurrency :Currency
    fun createTripItemsFromResponse(rawResponse: FlightsResponse): List<TripItem> {
        for (cur in rawResponse.agents) {
            agents.append(cur.id, cur)
        }
        for (cur in rawResponse.places) {
            places.append(cur.id,cur)
        }
        for (cur in rawResponse.carriers) {
            carriers.append(cur.id,cur)
        }
        for (cur in rawResponse.segments) {
            segments.append(cur.id,cur)

        }
        for(cur in rawResponse.currencies){
            currencies[cur.code]=cur
        }
        for(cur in rawResponse.legs){
            legs[cur.id]=processLeg(cur)
        }

        selectedCurrency = currencies[rawResponse.query.currency]!!

        val tripItems = ArrayList<TripItem>()

        for(itinerary in rawResponse.itineraries){
            addTripItemsForItenrary(tripItems,itinerary)
        }


        println()
        return tripItems

    }

    private fun addTripItemsForItenrary(tripItems: ArrayList<TripItem>, itinerary: ResponseItinerary) {
        val outBoundLeg = legs[itinerary.outboundLegId]!!
        val inBoundLeg = legs[itinerary.inboundLegId]!!
        for (pricingOption in itinerary.pricingOptions){
            val agent = agents[pricingOption.agents[0]]
            val price = pricingOption.price
            tripItems.add(
                TripItem(
                    agent,
                    outBoundLeg,
                    inBoundLeg,
                    price,
                    selectedCurrency
                )
            )
        }

    }

    private fun processLeg(responseLeg: ResponseLeg): ProcessedLeg {
        val departureTime = responseLeg.departureTime
        val arrivalTime = responseLeg.arrivalTime
        val durationMinutes = responseLeg.durationMinutes
        val departurePlace = places[responseLeg.originStation]
        val arrivalPlace = places[responseLeg.destinationStation]
        val stops = responseLeg.segmentIds.size-1
        val carrier = carriers[responseLeg.carriers[0]]
        return ProcessedLeg(
            departureTime,
            arrivalTime,
            durationMinutes,
            stops,
            departurePlace,
            arrivalPlace,
            carrier
        )
    }


}