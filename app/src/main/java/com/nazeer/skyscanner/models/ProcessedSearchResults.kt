package com.nazeer.skyscanner.models

data class ProcessedSearchResults(val tripItems: MutableList<TripItem>, val query: ProcessedQuery)