package com.myke.android.browsr.movies


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.data.PaginatedListResponse
import com.myke.android.browsr.network.NetworkHelper
import com.myke.android.browsr.utils.Constants.TAG
import com.myke.android.browsr.utils.EspressoIdlingResource.wrapEspressoIdlingResource
import com.myke.android.browsr.utils.Resource
import com.myke.android.browsr.base.BrowsrViewModel
import com.myke.android.browsr.domain.GetMovieDetailsUseCase
import com.myke.android.browsr.domain.GetPopularMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieListViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val networkHelper: NetworkHelper?
) : BrowsrViewModel() {

    private val _movieList = MutableLiveData<Resource<PaginatedListResponse<Movie>>>()
    val movieList: LiveData<Resource<PaginatedListResponse<Movie>>>
        get() = _movieList

    private val _movie = MutableLiveData<Resource<Movie>>()
    val movie: LiveData<Resource<Movie>>
        get() = _movie


    init {
        Log.d(TAG, "Initializing movie list view model...")
        isLoading.value = false
    }


    fun fetchMovies() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                _movieList.postValue(Resource.loading(null))
                if (networkHelper?.isNetworkConnected() == true) {
                    getPopularMoviesUseCase.run().let {
                        if (it.isSuccessful) {
                            it.body()?.let { results ->
                                Log.i(TAG, "Successfully Retrieved ${results.totalResults} movies")
                                _movieList.postValue(Resource.success(results))
                            }
                        } else {
                            Log.e(
                                TAG,
                                "Error fetching online movie records: ${it.errorBody().toString()} "
                            )
                            _movieList.postValue(Resource.error(it.errorBody().toString(), null))
                        }
                    }
                }
            }
        }
    }

    fun fetchSelectedMovies(id: Long) {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                _movie.postValue(Resource.loading(null))
                if (networkHelper?.isNetworkConnected() == true) {
                    getMovieDetailsUseCase.run(id).let {
                        if (it.isSuccessful) {
                            it.body()?.let { results ->
                                _movie.postValue(Resource.success(results))
                            }
                        } else {
                            Log.e(
                                TAG,
                                "Error fetching online movie records: ${it.errorBody().toString()} "
                            )
                            _movie.postValue(Resource.error(it.errorBody().toString(), null))
                        }
                    }
                }
            }
        }
    }
}