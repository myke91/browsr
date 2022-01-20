 package com.zenjob.android.browsr.network

import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.PaginatedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

 interface ApiService {

     @GET("movie/popular")
     suspend fun getPopularTvShows(
         @Query("language") query: String? = null,
         @Query("page") page: Int? = null
     ): Response<PaginatedListResponse<Movie>>


     @GET("movie/{movie_id}")
     suspend fun getDetails(
         @Path("movie_id") movieId: Long,
         @Query("language") query: String? = null
     ): Response<Movie>
}