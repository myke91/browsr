package com.zenjob.android.browsr.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.PaginatedListResponse
import com.zenjob.android.browsr.network.NetworkHelper
import com.zenjob.android.browsr.repository.MovieRepository
import com.zenjob.android.browsr.utils.Constants.TAG
import com.zenjob.android.browsr.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieListViewModel(
    private val repository: MovieRepository,
    private val networkHelper: NetworkHelper?
) : BrowsrViewModel() {
    var job: Job? = null

    private val _movieList = MutableLiveData<Resource<PaginatedListResponse<Movie>>>()
    val movieList: LiveData<Resource<PaginatedListResponse<Movie>>>
        get() = _movieList


    init {
        Log.d(TAG, "Initializing movie list view model...")
        isLoading.value = false
    }


     fun fetchMovies() {
        job = viewModelScope.launch {
            _movieList.postValue(Resource.loading(null))
            if (networkHelper?.isNetworkConnected() == true) {
                repository.getPopularTvShows().let {
                    if (it.isSuccessful) {
                        it.body()?.let { results ->
                            Log.i(TAG, "Successfully Retrieved ${results.totalResults} movies")
                            _movieList.postValue(Resource.success(results))
                        }
                    } else {
                        Log.e(TAG, "Error fetching online movie records: ${it.errorBody().toString()} ")
                        _movieList.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            }
        }
    }

    override fun onCleared() {
        Log.d(TAG, "Cancelling courotine job...")
        super.onCleared()
        job?.cancel()
    }

}