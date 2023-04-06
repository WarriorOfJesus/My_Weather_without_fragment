package com.example.myweather.main_page.repository

import android.util.Log
import android.widget.Toast
import com.example.myweather.main_page.api.WeatherApi
import com.example.myweather.main_page.api.models.WeatherDataResponse
import com.example.myweather.main_page.model.WeatherConverter
import com.example.myweather.main_page.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRemoteRepository(
    private val api: WeatherApi
) : WeatherRepository {
    private lateinit var weatherData: WeatherData

    override fun getWeatherData(city: String, key: String): WeatherData {
        api.getWeatherData(city, key).enqueue(object : Callback<WeatherDataResponse> {
            override fun onResponse(
                call: Call<WeatherDataResponse>,
                response: Response<WeatherDataResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    weatherData = WeatherConverter.fromNetwork(response.body())
                }
            }
            override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                Log.e("WeatherData", "onResponse: ${t.message}", t)
            }
        })
        return weatherData
    }
}