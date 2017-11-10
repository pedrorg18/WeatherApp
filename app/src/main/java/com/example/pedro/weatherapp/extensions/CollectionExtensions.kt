package com.example.pedro.weatherapp.extensions

fun <K, V : Any> MutableMap<K, V?>.ToVarargArray():
        Array<out Pair<K, V>> = map({Pair(it.key, it.value!!)}).toTypedArray()