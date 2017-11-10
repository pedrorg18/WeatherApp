package com.example.pedro.weatherapp.data.db

import android.database.sqlite.SQLiteDatabase
import com.example.pedro.weatherapp.domain.model.ForecastList
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
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

    fun <T : Any> SelectQueryBuilder.parseList(
            parser: (Map<String, Any?>) -> T): List<T> =
            parseList(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    fun <T : Any> SelectQueryBuilder.parseOpt(
            parser: (Map<String, Any?>) -> T): T? =
            parseOpt(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    //We inline the result of use with '=' because it returns Unit. Then this method returns Unit as well
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {

        }
    }

    private fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }


}
