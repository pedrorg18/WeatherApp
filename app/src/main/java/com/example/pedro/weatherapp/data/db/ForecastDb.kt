package com.example.pedro.weatherapp.data.db

import com.example.pedro.weatherapp.domain.model.ForecastList
import com.example.pedro.weatherapp.extensions.parseList
import com.example.pedro.weatherapp.extensions.parseOpt
import com.example.pedro.weatherapp.extensions.toVarargArray
import com.example.pedro.weatherapp.extensions.clear
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(
    private var forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
    private val dataMapper : DbDataMapper = DbDataMapper()) {

    //function that requests a forecast based on a zip code and a date
    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? " +
                "AND ${DayForecastTable.DATE} >= ?"

        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt{ CityForecast(HashMap(it), dailyForecast) }

        //return
        if (city != null) dataMapper.convertToDomain(city) else null
    }

    //We inline the result of use with '=' because it returns Unit. Then this method returns Unit as well
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }

}

