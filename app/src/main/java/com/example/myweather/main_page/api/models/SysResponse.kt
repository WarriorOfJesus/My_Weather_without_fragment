package com.example.myweather.main_page.api.models

data class SysResponse(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
