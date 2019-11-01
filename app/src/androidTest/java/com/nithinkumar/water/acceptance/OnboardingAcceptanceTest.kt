package com.nithinkumar.water.acceptance

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.nithinkumar.water.Activity.WaterActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@LargeTest
class OnboardingAcceptanceTest {

    @get:Rule
    var activityRule = ActivityTestRule(WaterActivity::class.java)

    @Before
    fun setUp() {
        // We started the activity in order to clear the shared preferences
        AndroidTestHelpers.clearSharedPreferences(activityRule.activity)

        // But, now that we've cleared the shared preferences, finish the activity
        activityRule.finishActivity()
    }

    @After
    fun tearDown() {
        activityRule.activity?.run { AndroidTestHelpers.clearSharedPreferences(this) }
    }

    @Test
    fun theFirstTimeYouOpenTheApp_youSeeTheFirstOnboardingPage() {
        activityRule.launchActivity(null)

        onView(withText("First")).check(matches(isDisplayed()))
    }

    @Test
    fun theSecondTimeYouOpenTheApp_youDoNotSeeTheOnboardingPages() {
        activityRule.launchActivity(null)
        activityRule.finishActivity()
        activityRule.launchActivity(null)

        onView(withText("First")).check(doesNotExist())
    }
}
