package com.zenjob.android.browsr.ui

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.ui.list.ListActivity
import com.zenjob.android.browsr.ui.list.MovieListAdapter
import com.zenjob.android.browsr.utils.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class ListActivityTest {
    @get:Rule val activityScenarioRule = activityScenarioRule<ListActivity>()

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun a_ensureRecyclerViewExist() {
        onView(withId(R.id.list)).isDisplayed()
    }

    @Test
    fun b_test_selectListItem_isDetailViewVisible() {
        onView(withId(R.id.list))
            .perform(
                actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.title)).check(matches(not(withText(""))))
    }

    @Test
    fun c_test_backNavigation_toMovieList() {
        onView(withId(R.id.list))
            .perform(
                actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.title)).check(matches(not(withText(""))))

        pressBack()

        onView(withId(R.id.list)).isDisplayed()
    }
}


