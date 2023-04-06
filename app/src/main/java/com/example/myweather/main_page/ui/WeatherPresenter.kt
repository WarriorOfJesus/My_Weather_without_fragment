package com.example.myweather.main_page.ui

import android.util.Log
import com.example.myweather.main_page.interactor.WeatherInteractor
import com.example.myweather.main_page.model.WeatherData

class WeatherPresenter(
    private val interactor: WeatherInteractor
) : WeatherContract.Presenter, BasePresenter<WeatherContract.View>() {

    override fun getDataFromApi(cityName: String, key: String) {
        view?.showLoading(true)
        if (cityName.length >= 3) {
            try {
                val weather = interactor.getWeatherData(cityName, key)
                view?.showWeatherData(weather)
                view?.showLoading(false)
            } catch (t: Throwable) {
//                Toast.makeText( t.message, Toast.LENGTH_SHORT).show()
                Log.e("Error get weather data ", t.message.toString())
                view?.showErrorMessage()
           }
        }
    }

}
