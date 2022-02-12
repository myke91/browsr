package com.myke.android.browsr.domain

import com.myke.android.browsr.core.usecasetypes.BaseUseCaseWithParams
import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.data.repository.MovieRepository
import com.myke.android.browsr.util.EspressoIdlingResource.wrapEspressoIdlingResource
import retrofit2.Response
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) :
    BaseUseCaseWithParams<Long, Response<Movie>> {
    override suspend fun run(params: Long): Response<Movie> {
        wrapEspressoIdlingResource {
            return repository.getDetails(params)
        }
    }


}