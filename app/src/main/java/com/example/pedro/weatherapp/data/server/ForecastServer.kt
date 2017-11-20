package com.example.pedro.weatherapp.data.server

import com.example.pedro.weatherapp.data.db.ForecastDb
import com.example.pedro.weatherapp.domain.datasource.ForecastDataSource
import com.example.pedro.weatherapp.domain.model.ForecastList

class ForecastServer(
        private val dataMapper: ServerDataMapper = ServerDataMapper(),
        private  val forecastDb: ForecastDb = ForecastDb())
    : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long):
            ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

}