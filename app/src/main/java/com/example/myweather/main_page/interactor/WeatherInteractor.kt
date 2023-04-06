package com.example.myweather.main_page.interactor

import com.example.myweather.main_page.model.WeatherData
import com.example.myweather.main_page.repository.WeatherRemoteRepository

class WeatherInteractor(
    private val remoteRepository: WeatherRemoteRepository
) {
    fun getWeatherData(city: String, key: String):WeatherData{
        return remoteRepository.getWeatherData(city,key)
    }
}