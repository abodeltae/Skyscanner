package com.nazeer.skyscanner.presentation

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.Toolbar
import com.nazeer.skyscanner.models.ProcessedSearchResults

interface SRPresenter {
    fun connectScreen(screen: SRScreen)
    fun destroy()
}

interface SRScreen {
    fun bind(processedSearchResults: ProcessedSearchResults)
    fun showLoading(show: Boolean)
    fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater): Boolean
    fun getToolbar(): Toolbar?
    fun displayError()
}