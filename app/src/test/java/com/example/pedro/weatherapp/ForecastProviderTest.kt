package com.example.pedro.weatherapp

import com.example.pedro.weatherapp.domain.datasource.ForecastDataSource
import com.example.pedro.weatherapp.domain.datasource.ForecastProvider
import com.example.pedro.weatherapp.domain.model.Forecast
import com.example.pedro.weatherapp.domain.model.ForecastList
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock

import org.mockito.Mockito.`when` as whenever


class ForecastProviderTest {


    @Test fun testDataSourceReturnsValue(){
        val ds = mock(ForecastDataSource::class.java)
        whenever(ds.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }

        val provider = ForecastProvider(listOf(ds))

        val dayFc = provider.requestForecast(0)
        Assert.assertNotNull(dayFc)
        Assert.assertEquals(dayFc.description, "desc")
    }

    @Test fun emptyDatabaseReturnsServerValue() {
        val db = mock(ForecastDataSource::class.java)

        val server = mock(ForecastDataSource::class.java)
        whenever(server.requestForecastByZipCode(
                any(Long::class.java), any(Long::class.java)))
        .then{
            ForecastList(0, "city", "country", listOf())
        }

        Assert.assertNotNull(
                ForecastProvider(listOf(db, server)).requestByZipCode(0, 0))
    }
}