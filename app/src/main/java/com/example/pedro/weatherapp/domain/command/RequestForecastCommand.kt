package com.example.pedro.weatherapp.domain.command

import com.example.pedro.weatherapp.data.ForecastRequest
import com.example.pedro.weatherapp.domain.mappers.ForecastDataMapper
import com.example.pedro.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode : String) :
        Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertToDataModel(
                forecastRequest.execute())
    }

}