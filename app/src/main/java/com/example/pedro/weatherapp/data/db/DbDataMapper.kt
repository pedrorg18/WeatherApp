package com.example.pedro.weatherapp.data.db

import com.example.pedro.weatherapp.domain.model.Forecast
import com.example.pedro.weatherapp.domain.model.ForecastList

class DbDataMapper {

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)

    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date, description, high, low, iconUrl)
    }

    fun convertFromDomain(forecastList: ForecastList) = with(forecastList) {
        val dailyForecasts = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, dailyForecasts)
    }

    private fun convertDayFromDomain(cityId: Long, domainDayForecast: Forecast) =
            with(domainDayForecast) {
                DayForecast(date, description, high, low, iconUrl, cityId)
            }
}