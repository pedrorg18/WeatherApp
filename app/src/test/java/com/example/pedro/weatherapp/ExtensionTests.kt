package com.example.pedro.weatherapp

import com.example.pedro.weatherapp.extensions.toDateString
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

class ExtensionTests {

    @Test
    fun testLongToDateString() {
        assertEquals((1512210287386L).toDateString(), "02-dic-2017")
    }

    @Test
    fun testDateStringFullFormat() {
        assertEquals(1512210686235L.toDateString(DateFormat.FULL),
                "sábado 2 de diciembre de 2017")
    }
}