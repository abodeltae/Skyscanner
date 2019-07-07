package com.nazeer.skyscanner

import android.content.Context
import android.util.TypedValue
import java.util.*


object Utils {
    fun dpToPx(context: Context, dip: Float): Int {
        val r = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            r.displayMetrics
        ).toInt()
    }

    fun nextDayOfWeek(dow: Int): Calendar {
        val date = Calendar.getInstance()
        var diff = dow - date.get(Calendar.DAY_OF_WEEK)
        if (diff <= 0) {
            diff += 7
        }
        date.add(Calendar.DAY_OF_MONTH, diff)
        return date
    }

}