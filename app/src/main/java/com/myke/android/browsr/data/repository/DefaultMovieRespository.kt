package com.myke.android.browsr.data.repository

import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.data.PaginatedListResponse
import com.myke.android.browsr.data.datasource.remote.MoviesRemoteDatasource
import retrofit2.Response
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(private val datasource: MoviesRemoteDatasource) : MovieRepository {
    override suspend fun getPopularTvShows(
        query: String?,
        page: Int?
    ): Response<PaginatedListResponse<Movie>> = datasource.getPopularTvShows(query, page)

    override suspend fun getDetails(movieId: Long, query: String?): Response<Movie> =
        datasource.getDetails(movieId, query)

}