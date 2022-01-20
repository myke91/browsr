package com.zenjob.android.browsr.repository

import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.PaginatedListResponse
import com.zenjob.android.browsr.network.ApiHelper
import retrofit2.Response

class DefaultMovieRepository(private val apiHelper: ApiHelper) : MovieRepository {
    override suspend fun getPopularTvShows(
        query: String?,
        page: Int?
    ): Response<PaginatedListResponse<Movie>> = apiHelper.getPopularTvShows(query, page)

    override suspend fun getDetails(movieId: Long, query: String?): Response<Movie> =
        apiHelper.getDetails(movieId, query)

}