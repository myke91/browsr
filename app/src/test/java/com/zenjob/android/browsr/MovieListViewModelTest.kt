package com.zenjob.android.browsr

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zenjob.android.browsr.viewmodel.MovieListViewModel
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import java.util.concurrent.TimeUnit


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest : TestCase() {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var initRule: MockitoRule = MockitoJUnit.rule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var repository: FakeCarRepository
    private var movieListViewModel: MovieListViewModel? = null

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeCarRepository()
        movieListViewModel = MovieListViewModel(repository, null)
    }

    @After
    public override fun tearDown() {
        super.tearDown()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when call fetchMovies, populate movie list`() {
        movieListViewModel?.fetchMovies()

        movieListViewModel?.movieList?.getOrAwaitValue(2, TimeUnit.SECONDS)
    }



}