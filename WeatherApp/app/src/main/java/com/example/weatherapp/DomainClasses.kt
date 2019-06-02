package com.example.weatherapp

data class ForecastVmList(val id: Long, val city: String, val country: String, val dailyForecast: List<ForecastVm>)
{
    val size: Int get() = dailyForecast.size

    operator fun get(position: Int): ForecastVm = dailyForecast[position]
}

data class ForecastVm(val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)
