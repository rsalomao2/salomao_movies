package com.salomao.movies.data.service

import com.salomao.movies.BuildConfig
import com.salomao.movies.data.remote.MovieDetailResponse
import com.salomao.movies.data.remote.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("${BuildConfig.MOVIE_DB_API_VERSION}/movie/popular")
    suspend fun fetchMovieList(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("per_page") perPage: Int
    ): MovieListResponse

    @GET("${BuildConfig.MOVIE_DB_API_VERSION}/movie/{movieId}")
    suspend fun fetchMovieDetails(@Path("movieId") movieId: Int): MovieDetailResponse
}