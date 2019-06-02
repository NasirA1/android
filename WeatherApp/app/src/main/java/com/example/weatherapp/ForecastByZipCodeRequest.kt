package com.example.weatherapp

import android.util.Log
import com.google.gson.Gson
import java.net.URL

class ForecastByZipCodeRequest(private val zipCode: Long) {

    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val COMPLETE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?APPID=$APP_ID&mode=json&units=metric&cnt=7&zip="
    }

    fun get(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        val result = Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
        Log.d(APP_TITLE, result.toString())
        return result
    }
}