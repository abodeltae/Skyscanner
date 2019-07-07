package com.nazeer.skyscanner

import com.nazeer.skyscanner.api.RetrofitClient
import com.nazeer.skyscanner.api.SkyScannerService
import com.nazeer.skyscanner.presentation.SearchResultPresenter
import com.nazeer.skyscanner.repo.FlightsRepo
import com.nazeer.skyscanner.repo.FlightSearchResultProcessor
import com.nazeer.skyscanner.repo.RepoClient
import com.nazeer.skyscanner.repo.RepoRemoteClient

object DependencyManager {

    private fun getRepo(): FlightsRepo {
        val remoteClient = getRemoteClient()
        return FlightsRepo(remoteClient)
    }

    private fun getRemoteClient(): RepoClient {
        val service = getService()
        val converter = rawFlightsResponseToTripITamsConverter()
        return RepoRemoteClient(service, converter)
    }

    private fun rawFlightsResponseToTripITamsConverter(): FlightSearchResultProcessor {
        return FlightSearchResultProcessor()
    }

    private fun getService (): SkyScannerService{
        val retrofitClient = RetrofitClient()
        return retrofitClient.getSkyScannerService()
    }

    fun getSearchResultsPresenter(): SearchResultPresenter {
        val repo = getRepo()
        return SearchResultPresenter(repo)
    }

}