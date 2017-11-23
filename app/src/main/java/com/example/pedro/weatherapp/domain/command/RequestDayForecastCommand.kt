package com.example.pedro.weatherapp.domain.command

import com.example.pedro.weatherapp.domain.datasource.ForecastProvider
import com.example.pedro.weatherapp.domain.model.Forecast

class RequestDayForecastCommand(val id: Long,
                                private val forecastProvider: ForecastProvider = ForecastProvider()) :
                                Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}