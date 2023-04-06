package com.example.myweather.main_page.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myweather.BlankFragment
import com.example.myweather.R
import com.example.myweather.databinding.ActivityMainBinding
import com.example.myweather.utils.RetrofitClient
import com.example.myweather.main_page.api.WeatherApi
import com.example.myweather.main_page.interactor.WeatherInteractor
import com.example.myweather.main_page.model.WeatherData
import com.example.myweather.main_page.repository.WeatherRemoteRepository
import kotlin.math.roundToInt

class WeatherActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
        val fragment = WeatherFragment()
        val fragment2 = BlankFragment()
        changeFragment(fragment2, R.id.fragmentContainer)
    }

    private fun changeFragment(fragment: Fragment, id: Int) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(id, fragment)
            .commit()
    }
}

