package com.example.pedro.weatherapp.domain.model

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)

data class ForecastList(val city: String, val country: String,
                        private val dailyForecast: List<Forecast>) {

    val size: Int
        get() = dailyForecast.size

    operator fun get(position : Int) : Forecast = dailyForecast[position]
}

