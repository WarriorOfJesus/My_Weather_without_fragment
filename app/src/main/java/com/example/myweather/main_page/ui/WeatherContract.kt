package com.example.myweather.main_page.ui

import com.example.myweather.main_page.model.WeatherData

interface WeatherContract:BaseFragmentContract {
    interface View : MvpView {
        fun showWeatherData(data: WeatherData)
        fun showFailure(t: Throwable)
        fun showLoading(isLoading: Boolean)
        fun showInfo()
//        fun isVisible(isVisible:Boolean)
    }

    interface Presenter:MvpPresenter<View> {
        fun getDataFromApi(cityName: String, key: String)
    }
}