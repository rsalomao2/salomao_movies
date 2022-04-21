package com.salomao.movies.data.service

import com.salomao.movies.BuildConfig
import com.salomao.movies.remote.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("${BuildConfig.MOVIE_DB_API_VERSION}/movie/popular")
    suspend fun getMovieList(
        @Query("page") page: Int,
        @Query("language") language: String,
    ): MovieListResponse
}
