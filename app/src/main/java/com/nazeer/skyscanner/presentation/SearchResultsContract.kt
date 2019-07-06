package com.nazeer.skyscanner.presentation

import com.nazeer.skyscanner.models.ProcessedSearchResults

interface SRPresenter {
    val srScreen: SRScreen
}

interface SRScreen {
    fun bind(processedSearchResults: ProcessedSearchResults)
}