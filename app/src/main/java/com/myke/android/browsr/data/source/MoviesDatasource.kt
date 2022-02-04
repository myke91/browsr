package com.myke.android.browsr.data.source

import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.data.PaginatedListResponse
import retrofit2.Response

interface MoviesDatasource {

    suspend fun getPopularTvShows(query: String? = null, page: Int? = null): Response<PaginatedListResponse<Movie>>

    suspend fun getDetails(movieId: Long, query: String? = null): Response<Movie>
}