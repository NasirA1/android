package com.example.weatherapp


class RequestForecastCommand(
    private val zipCode: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()) :
    Command<ForecastVmList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute(): ForecastVmList = forecastProvider.requestByZipCode(zipCode, DAYS)
}
