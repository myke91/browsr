package com.myke.android.browsr.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myke.android.browsr.R
import com.myke.android.browsr.movies.detail.DetailFragment
import com.myke.android.browsr.movies.list.ListFragment
import com.myke.android.browsr.movies.list.MovieListAdapter
import com.myke.android.browsr.utils.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class ListFragmentTest {
     val scenario = launchFragmentInContainer<ListFragment>()


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


