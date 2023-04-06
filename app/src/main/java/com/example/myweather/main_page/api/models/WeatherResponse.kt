package com.example.myweather.main_page.api.models

data class WeatherResponse(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
