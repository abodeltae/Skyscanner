package com.nazeer.skyscanner.presentation

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.nazeer.skyscanner.DependencyManager
import com.nazeer.skyscanner.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import android.widget.Toast
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    private lateinit var screen: SRScreenImp
    lateinit var presenter: SRPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screen = SRScreenImp(this)
        setContentView(screen)
        screen.getToolbar()?.let {
            setSupportActionBar(screen.getToolbar())
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        if (lastCustomNonConfigurationInstance == null) {
            presenter = DependencyManager.getSearchResultsPresenter()
        } else {
            presenter = lastCustomNonConfigurationInstance as SRPresenter
        }
        presenter.connectScreen(screen)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return screen.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> finish()
        }
        return true
    }
}
