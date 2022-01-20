package com.zenjob.android.browsr.network

import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.PaginatedListResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getPopularTvShows(
        query: String? = null,
        page: Int? = null
    ): Response<PaginatedListResponse<Movie>>

    suspend fun getDetails(movieId: Long, query: String? = null): Response<Movie>
}