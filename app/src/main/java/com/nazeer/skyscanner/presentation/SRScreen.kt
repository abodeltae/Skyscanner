package com.nazeer.skyscanner.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nazeer.skyscanner.R

class SRScreenImp @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.sr_screen_layout,this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewSrScreen)
        recyclerView.layoutManager = LinearLayoutManager(context)


    }


}