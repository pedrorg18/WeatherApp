package com.example.pedro.weatherapp.domain.datasource

import com.example.pedro.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}