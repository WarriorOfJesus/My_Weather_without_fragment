package com.example.myweather.main_page.api

import com.example.myweather.main_page.api.models.WeatherDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("q") cityName: String,
        @Query("appid") apiKey : String
    ): Call<WeatherDataResponse>
}