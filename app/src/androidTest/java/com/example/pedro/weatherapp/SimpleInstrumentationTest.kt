package com.example.pedro.weatherapp

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.pedro.weatherapp.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test

class SimpleInstrumentationTest {

    // Int Kotlin need to add get because field is not public and it is not a kotlin property
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun itemClick_navigatesToDetail() {
        // create view interaction with forecastList in main act
        Espresso.onView(withId(R.id.forecastList)).perform(
                RecyclerViewActions
                        // click first item of the listView
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        // description in det act
        onView(withId(R.id.weatherDescription)).check(
                // check it is a TextView
                matches(isAssignableFrom(TextView::class.java))
        )
    }
}