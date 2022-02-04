package com.myke.android.browsr.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
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
    fun testNavigationToInGameScreen() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<DetailFragment>()

        titleScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.movies_navigation)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

//        // Verify that performing a click changes the NavController’s state
//        onView(withId(R.id.play_btn)).perform(ViewActions.click())
//        assertThat(navController.currentDestination?.id).isEqualTo(R.id.in_game)

        onView(withId(R.id.movie_image)).isDisplayed()
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


