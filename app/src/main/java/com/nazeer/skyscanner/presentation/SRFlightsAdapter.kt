package com.nazeer.skyscanner.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazeer.skyscanner.models.TripItem

class SRFlightsAdapter(private val context: Context) : RecyclerView.Adapter<SRFlightsAdapter.VH>() {
    private val items = ArrayList<TripItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val card = SearchResultCard(context)
        return VH(card)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.searchResultCard.bind(items[position])
    }

    fun setItems(newItems: List<TripItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class VH(val searchResultCard: SearchResultCard) : RecyclerView.ViewHolder(searchResultCard)

}
