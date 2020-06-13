package com.example.forecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.forecast.data.db.entity.CONST_WEATHER_ID
import com.example.forecast.data.db.entity.CurrentWeatherEntry
import com.example.forecast.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.example.forecast.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)
    @Query("select * from current_weather where id = $CONST_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = $CONST_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}