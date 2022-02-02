package com.myke.android.browsr

import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.data.PaginatedListResponse
import com.myke.android.browsr.data.repository.MovieRepository
import retrofit2.Response
import java.util.Date

class FakeMovieRepository : MovieRepository {

    override suspend fun getPopularTvShows(
        query: String?,
        page: Int?
    ): Response<PaginatedListResponse<Movie>> {
        val response = PaginatedListResponse<Movie>(
            page = 1,
            totalResults = 2,
            totalPages = 1,
            results = listOf(
                Movie(
                    id = 1L,
                    imdbId = "",
                    overview = "simple overview 1",
                    title = "Good movie 1",
                    posterPath = "<some link to image>",
                    backdropPath = "<some link to image>",
                    releaseDate = Date(),
                    voteAverage = 0.1f
                ),
                Movie(
                    id = 1L,
                    imdbId = "",
                    overview = "simple overview 2",
                    title = "Good movie 2",
                    posterPath = "<some link to image>",
                    backdropPath = "<some link to image>",
                    releaseDate = Date(),
                    voteAverage = 0.1f
                )
            ),
        )

        return Response.success(response)
    }

    override suspend fun getDetails(movieId: Long, query: String?): Response<Movie> {
        val response = Movie(
            id = 1L,
            imdbId = "",
            overview = "simple overview 1",
            title = "Good movie 1",
            posterPath = "<some link to image>",
            backdropPath = "<some link to image>",
            releaseDate = Date(),
            voteAverage = 0.1f
        )
        return Response.success(response)
    }
}