package com.example.pedro.weatherapp.domain.datasource

import com.example.pedro.weatherapp.data.db.ForecastDb
import com.example.pedro.weatherapp.data.server.ForecastServer
import com.example.pedro.weatherapp.domain.model.ForecastList
import com.example.pedro.weatherapp.extensions.firstResult

class ForecastProvider (private  val sources: List<ForecastDataSource> =
        ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int) : ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long) = requestToSources {
        it.requestDayForecast(id)
    }

    fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long):
            ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T =
            sources.firstResult { f(it) }


    private fun todayTimeSpan() = System.currentTimeMillis() /
            DAY_IN_MILLIS * DAY_IN_MILLIS
}