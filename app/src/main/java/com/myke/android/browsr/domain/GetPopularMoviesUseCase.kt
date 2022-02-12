package com.myke.android.browsr.domain

import com.myke.android.browsr.core.usecasetypes.BaseUseCaseWitOuthParams
import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.data.PaginatedListResponse
import com.myke.android.browsr.data.repository.MovieRepository
import com.myke.android.browsr.util.EspressoIdlingResource.wrapEspressoIdlingResource
import retrofit2.Response
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository) :
    BaseUseCaseWitOuthParams<Response<PaginatedListResponse<Movie>>> {

    override suspend fun run(): Response<PaginatedListResponse<Movie>> {
        wrapEspressoIdlingResource {
            return repository.getPopularTvShows()
        }
    }
}