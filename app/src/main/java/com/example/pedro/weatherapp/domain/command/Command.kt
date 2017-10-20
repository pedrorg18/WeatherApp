package com.example.pedro.weatherapp.domain.command

public interface Command<out T> {
    fun execute(): T
}