package com.nazeer.skyscanner

import com.nazeer.skyscanner.api.RetrofitClient
import com.nazeer.skyscanner.api.SkyScannerService
import com.nazeer.skyscanner.repo.FlightsRepo
import com.nazeer.skyscanner.repo.RawFlightsResponseToTripITamsConverter
import com.nazeer.skyscanner.repo.RepoClient
import com.nazeer.skyscanner.repo.RepoRemoteClient

object DependencyManager {

    fun getRepo(): FlightsRepo {
        val remoteClient = getRemoteClient()
        return FlightsRepo(remoteClient)
    }

    private fun getRemoteClient(): RepoClient {
        val service = getService()
        val converter = rawFlightsResponseToTripITamsConverter()
        return RepoRemoteClient(service, converter)
    }

    private fun rawFlightsResponseToTripITamsConverter(): RawFlightsResponseToTripITamsConverter {
        return RawFlightsResponseToTripITamsConverter()
    }

    private fun getService (): SkyScannerService{
        val retrofitClient = RetrofitClient()
        return retrofitClient.getSkyScannerService()
    }

}