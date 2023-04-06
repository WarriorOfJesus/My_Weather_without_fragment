package com.example.myweather.main_page.repository

import com.example.myweather.main_page.model.WeatherData

interface WeatherRepository {
    fun getWeatherData(city: String, key: String): WeatherData
}