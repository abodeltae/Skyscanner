package com.nazeer.skyscanner.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.nazeer.skyscanner.R

class SearchResultCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr){
    init {
        LayoutInflater.from(context).inflate(R.layout.search_result_card,this)
    }
}