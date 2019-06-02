package com.example.weatherapp



interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastVmList?

}
