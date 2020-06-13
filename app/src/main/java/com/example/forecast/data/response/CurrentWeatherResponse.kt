package com.example.forecast.data.response

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName(value = "current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)