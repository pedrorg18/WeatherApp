package com.example.pedro.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

fun Context.color(color: Int) = ContextCompat.getColor(this, color)