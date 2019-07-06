package com.nazeer.skyscanner.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nazeer.skyscanner.DependencyManager
import com.nazeer.skyscanner.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = DependencyManager.getRepo()
        val service = repo.getFlights(
            "Economy",
            "UK",
            "GBP",
            "en-GB",
            "sky",
            "EDI-sky",
            "LOND-sky",
            "2020-05-30",
            "2020-06-02",
            1,
            0,
            0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response , error ->
                println("hi")
            }
    }
}
