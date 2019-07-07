package com.nazeer.skyscanner.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazeer.skyscanner.R
import com.nazeer.skyscanner.models.ProcessedQuery
import com.nazeer.skyscanner.models.ProcessedSearchResults
import kotlinx.android.synthetic.main.sr_screen_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class SRScreenImp @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), SRScreen {


    private val adapter: SRFlightsAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.sr_screen_layout, this)
        recyclerViewSrScreen.layoutManager = LinearLayoutManager(context)
        adapter = SRFlightsAdapter(context)
        recyclerViewSrScreen.adapter = adapter
    }

    override fun bind(processedSearchResults: ProcessedSearchResults) {
        adapter.setItems(processedSearchResults.tripItems)
        textViewSRCount.text = context.getString(
            R.string.res_count_from_total,
            processedSearchResults.tripItems.size,
            processedSearchResults.tripItems.size
        )
        bindToolbarTitles(processedSearchResults.query)
        cardViewResultsControlsSrScreen.visibility = View.VISIBLE
    }

    private fun bindToolbarTitles(query: ProcessedQuery) {
        val title = "${query.originPlace.name} - ${query.destinationPlace.name}"
        val dateFormatter = SimpleDateFormat("dd MMM", Locale("en-GB"))
        val formattedOutboundDate = dateFormatter.format(query.outboundTime)
        val formattedInboundDate = dateFormatter.format(query.inboundTime)
        val adultText = context.getString(R.string.adults_count, query.adults)
        val subtitle = "$formattedOutboundDate - $formattedInboundDate, $adultText, ${query.cabinClass}"
        textViewToolbarMainTitle.text = title
        textViewToolbarSubTitle.text = subtitle
        textViewToolbarSubTitle.visibility = View.VISIBLE
    }

    override fun showLoading(show: Boolean) {
        progressBarSrScreen.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater): Boolean {
        inflater.inflate(R.menu.sr_menu, menu)
        return true
    }

    override fun getToolbar(): Toolbar? {
        return toolbarSrScreen
    }

    override fun displayError() {
        Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_LONG).show()
    }


}