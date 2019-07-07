package com.nazeer.skyscanner.repo

import com.nazeer.skyscanner.api.SkyScannerService
import com.nazeer.skyscanner.models.ProcessedSearchResults
import io.reactivex.Single

class RepoRemoteClient(
    val skyScannerService: SkyScannerService,
    private val flightSearchResultProcessor: FlightSearchResultProcessor
) :
    RepoClient {
    override fun getFlights(
        cabinClass: String,
        country: String,
        currency: String,
        locale: String,
        locationSchema: String,
        originPlace: String,
        destinationPlace: String,
        outboundDate: String,
        inboundDate: String,
        adults: Int,
        children: Int,
        infants: Int
    ): Single<ProcessedSearchResults> {
      return  skyScannerService.createSession(
            cabinClass,
            country,
            currency,
            locale,
            locationSchema,
            originPlace,
            destinationPlace,
            outboundDate,
            inboundDate,
            adults,
            children,
            infants
        ).map {
            it.headers()["Location"]
        }.flatMap {
            val newUrl = it.replaceFirst("http", "https")
            skyScannerService.pollSession(newUrl)
        }.map {
          flightSearchResultProcessor.createProcessedTripSearchResults(it)
        }
    }



}