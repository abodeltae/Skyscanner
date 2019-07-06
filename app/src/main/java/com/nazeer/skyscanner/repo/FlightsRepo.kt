package com.nazeer.skyscanner.repo

import com.nazeer.skyscanner.models.ProcessedSearchResults
import io.reactivex.Single

interface RepoClient {
    fun getFlights(
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
    ): Single<ProcessedSearchResults>
}

class FlightsRepo(private val remoteRepoClient: RepoClient) :
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
        return remoteRepoClient.getFlights(
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
        )
    }
}