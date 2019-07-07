package com.nazeer.skyscanner.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import com.nazeer.skyscanner.R
import com.nazeer.skyscanner.Utils
import com.nazeer.skyscanner.models.TripItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_result_card.view.*
import java.text.SimpleDateFormat
import java.util.*


class SearchResultCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    init {

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val dp8 = Utils.dpToPx(context, 8f)
        params.setMargins(dp8, dp8 / 2, dp8, dp8 / 2)
        elevation = (dp8 / 2).toFloat()
        layoutParams = params
        radius = (dp8 / 2).toFloat()
        LayoutInflater.from(context).inflate(R.layout.search_result_card, this)
        setBackgroundColor(resources.getColor(android.R.color.white))
    }

    fun bind(tripItem: TripItem) {

        val inboundDuration = tripItem.inboundLeg.durationMinutes
        val outboundDuration = tripItem.outboundLeg.durationMinutes
        val dateFormat = SimpleDateFormat("HH:mm", Locale("en-GB"))
        val inboundPointsAndCarrierText =
            "${tripItem.inboundLeg.departurePlace.code} - ${tripItem.inboundLeg.arrivalPlace.code}, ${tripItem.inboundLeg.carrier.name}"
        val outBoundPointsAndCarrierText =
            "${tripItem.outboundLeg.departurePlace.code} - ${tripItem.outboundLeg.arrivalPlace.code}, ${tripItem.outboundLeg.carrier.name}"
        val inboundStops = tripItem.inboundLeg.stops
        val outBoundStops = tripItem.outboundLeg.stops

        textViewOutBoundTime.text =
            "${dateFormat.format(tripItem.outboundLeg.departureTime)} - ${dateFormat.format(tripItem.outboundLeg.arrivalTime)}"
        textViewInboundTime.text =
            "${dateFormat.format(tripItem.inboundLeg.departureTime)} - ${dateFormat.format(tripItem.inboundLeg.arrivalTime)}"
        textViewInboundPlacesAndCarrier.text = inboundPointsAndCarrierText
        textViewOutboundPlacesAndCarrier.text = outBoundPointsAndCarrierText
        textViewInboundDuration.text =
            context.getString(R.string.duration_hour_minute, inboundDuration / 60, inboundDuration % 60)
        textViewOutboundDuration.text =
            context.getString(R.string.duration_hour_minute, outboundDuration / 60, outboundDuration % 60)

        textViewPrice.text =
            context.getString(R.string.price_currency, tripItem.price.toInt(), tripItem.priceCurrency.symbol)
        Picasso.get().load(tripItem.inboundLeg.carrier.imageUrl).into(imageViewInboundCarrier)
        Picasso.get().load(tripItem.outboundLeg.carrier.imageUrl).into(imageViewOutBoundCarrier)

        textViewInboundType.text = if (inboundStops == 0) context.getString(R.string.direct)
        else context.resources.getQuantityString(R.plurals.flightStops, inboundStops, inboundStops)
        textViewOutboundType.text = if (outBoundStops == 0) context.getString(R.string.direct)
        else context.resources.getQuantityString(R.plurals.flightStops, outBoundStops, outBoundStops)
        textViewAgent.text = context.getString(R.string.via_agent, tripItem.agent.name)
    }
}