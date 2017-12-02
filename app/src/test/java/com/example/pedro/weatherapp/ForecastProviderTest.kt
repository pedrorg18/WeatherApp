package com.example.pedro.weatherapp

import com.example.pedro.weatherapp.domain.datasource.ForecastDataSource
import com.example.pedro.weatherapp.domain.datasource.ForecastProvider
import com.example.pedro.weatherapp.domain.model.Forecast
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

import org.mockito.Mockito.`when` as whenever


class ForecastProviderTest {


    @Test
    fun testDataSourceReturnsValue(){
        val ds = mock(ForecastDataSource::class.java)
        whenever(ds.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }

        val provider = ForecastProvider(listOf(ds))

        val dayFc = provider.requestForecast(0)
        Assert.assertNotNull(dayFc)
        Assert.assertEquals(dayFc.description, "desc")
    }
}