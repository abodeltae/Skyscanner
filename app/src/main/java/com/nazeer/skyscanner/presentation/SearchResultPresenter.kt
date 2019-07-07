package com.nazeer.skyscanner.presentation

import android.util.Log
import com.nazeer.skyscanner.Utils
import com.nazeer.skyscanner.models.ProcessedSearchResults
import com.nazeer.skyscanner.models.TripItem
import com.nazeer.skyscanner.repo.FlightsRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class SearchResultPresenter(private val rep: FlightsRepo) : SRPresenter {
    private val disposables = CompositeDisposable()
    private val TAG = this.javaClass.name
    private var screen: SRScreen? = null
    private var processedSearchResults: ProcessedSearchResults? = null

    override fun connectScreen(screen: SRScreen) {
        this.screen = screen
        showResults()
    }

    private fun showResults() {
        processedSearchResults?.let {
            screen?.bind(it)
        } ?: fetchAndShowResults()


    }

    private fun fetchAndShowResults() {
        screen?.showLoading(true)
        val nextMonday = Utils.nextDayOfWeek(Calendar.MONDAY)


        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en-GB"))
        val nextMondayText = formatter.format(nextMonday.time)
        nextMonday.add(Calendar.DAY_OF_MONTH, 1)
        val nextTuesdayText = formatter.format(nextMonday.time)

        disposables.add(
            rep.getFlights(
                "Economy",
                "UK",
                "GBP",
                "en-GB",
                "sky",
                "EDI-sky",
                "LOND-sky",
                nextMondayText,
                nextTuesdayText,
                1,
                0,
                0
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    sortOnPrice(res.tripItems)
                    processedSearchResults = res
                    screen?.bind(res)
                    screen?.showLoading(false)


                }, { throwable ->
                    Log.e(TAG, throwable.message)
                    screen?.showLoading(false)
                    screen?.displayError()

                })
        )
    }

    private fun sortOnPrice(tripItems: MutableList<TripItem>) {
        tripItems.sortWith(Comparator { item1, item2 -> item1.price.compareTo(item2.price) })
    }

    override fun destroy() {
        screen = null
        disposables.dispose()
    }

}