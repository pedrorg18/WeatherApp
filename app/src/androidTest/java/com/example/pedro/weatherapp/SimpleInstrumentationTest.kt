package com.example.pedro.weatherapp

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.example.pedro.weatherapp.ui.activities.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test

class SimpleInstrumentationTest {

    // Int Kotlin need to add get because field is not public and it is not a kotlin property
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun itemClick_navigatesToDetail() {
        // create view interaction with forecastList in main act
        onView(withId(R.id.forecastList)).perform(
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

    @Test fun modifyZipCode_changesToolbarTitle() {
        // Open overflow menu
        openActionBarOverflowOrOptionsMenu(activityRule.activity)
        // click on settings option
        onView(withText(R.string.settings)).perform(click())
        // change zip
        onView(withId(R.id.cityCode)).perform(replaceText("94015"))
        pressBack()
        // check that toolbar..
        onView(isAssignableFrom(Toolbar::class.java))
                // has title with provided string
                .check(matches(withToolbarTitle(`is`("Daly City (US)"))))
    }

    private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> =
            object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {

                override fun matchesSafely(toolbar: Toolbar): Boolean =
                        textMatcher.matches(toolbar.title.toString())

                override fun describeTo(description: Description) {
                    description.appendText("with toolbar title: ")
                    textMatcher.describeTo(description)
                }
            }
}