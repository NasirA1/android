package com.example.weatherapp


class DbDataMapper {

    fun convertFromDomain(forecast: ForecastVmList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: ForecastVm) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastVmList(_id, city.toString(), country.toString(), daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        ForecastVm(date, description, high, low, iconUrl)
    }
}