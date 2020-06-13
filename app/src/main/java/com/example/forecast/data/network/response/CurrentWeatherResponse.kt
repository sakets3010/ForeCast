package com.example.forecast.data.network.response

import com.example.forecast.data.db.entity.CurrentWeatherEntry
import com.example.forecast.data.db.entity.Location
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)