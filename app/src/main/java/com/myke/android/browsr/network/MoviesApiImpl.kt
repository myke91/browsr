package com.myke.android.browsr.network

import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.data.PaginatedListResponse
import retrofit2.Response

class MoviesApiImpl(private val moviesService: MoviesService) : MoviesApi {

    override suspend fun getPopularTvShows(
        query: String?,
        page: Int?
    ): Response<PaginatedListResponse<Movie>> = moviesService.getPopularTvShows(query, page)

    override suspend fun getDetails(movieId: Long, query: String?): Response<Movie> = moviesService.getDetails(movieId, query)
}