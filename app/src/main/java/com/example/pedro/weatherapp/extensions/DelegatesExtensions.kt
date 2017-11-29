package com.example.pedro.weatherapp.extensions

import android.content.Context
import kotlin.reflect.KProperty

class LongPreference (private val context: Context,
                      private val name: String,
                      private val default: Long) {

    private val prefs by lazy {
        context.getSharedPreferences("default",  Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any, property: KProperty<*>): Long =
        prefs.getLong(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        prefs.edit().putLong(name, value).apply()
    }
}