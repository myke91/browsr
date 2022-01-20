package com.zenjob.android.browsr.ui

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

fun ViewInteraction.isDisplayed(): Boolean {
    try {
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return true
    } catch (e: NoMatchingViewException) {
        return false
    }
}
