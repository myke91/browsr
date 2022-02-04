package com.myke.android.browsr.data.source.remote

import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.data.PaginatedListResponse
import com.myke.android.browsr.network.MoviesApi
import com.myke.android.browsr.data.source.MoviesDatasource
import retrofit2.Response
import javax.inject.Inject

class MoviesRemoteDatasource @Inject constructor(val movieApi: MoviesApi) : MoviesDatasource {

    override suspend fun getPopularTvShows(
        query: String?,
        page: Int?
    ): Response<PaginatedListResponse<Movie>> = movieApi.getPopularTvShows(query, page)

    override suspend fun getDetails(movieId: Long, query: String?): Response<Movie> =
        movieApi.getDetails(movieId, query)
}