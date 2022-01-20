package com.zenjob.android.browsr.network

import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.PaginatedListResponse
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPopularTvShows(
        query: String?,
        page: Int?
    ): Response<PaginatedListResponse<Movie>> = apiService.getPopularTvShows(query, page)

    override suspend fun getDetails(movieId: Long, query: String?): Response<Movie> = apiService.getDetails(movieId, query)
}