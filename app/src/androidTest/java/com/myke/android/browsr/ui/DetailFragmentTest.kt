package com.myke.android.browsr.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myke.android.browsr.R
import com.myke.android.browsr.movies.detail.DetailFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

//    @get:Rule var scenario = launchFragmentInContainer<DetailFragment>()
    private lateinit var scenario: FragmentScenario<DetailFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.AppTheme)
    }


    @Test
    fun ensureMovieImageViewExist() {
        onView(withId(R.id.movie_image)).isDisplayed()
    }

    @Test
    fun ensureMovieTitleViewExist() {
        onView(withId(R.id.title)).isDisplayed()
    }

    @Test
    fun ensureRatingViewExist() {
        onView(withId(R.id.rating)).isDisplayed()
    }

    @Test
    fun ensureReleaseDateViewExist() {
        onView(withId(R.id.release_date)).isDisplayed()
    }

    @Test
    fun ensureDescriptionViewExist() {
        onView(withId(R.id.description)).isDisplayed()
    }
}


