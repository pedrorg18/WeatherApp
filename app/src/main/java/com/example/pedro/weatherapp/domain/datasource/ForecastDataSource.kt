package com.example.pedro.weatherapp.domain.datasource

import com.example.pedro.weatherapp.domain.model.Forecast
import com.example.pedro.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}