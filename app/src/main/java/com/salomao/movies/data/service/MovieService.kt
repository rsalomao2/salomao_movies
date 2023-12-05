package com.salomao.movies.data.service

import com.salomao.movies.data.remote.MovieDetailResponse
import com.salomao.movies.data.remote.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/popular")
    suspend fun fetchMovieList(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("per_page") perPage: Int
    ): MovieListResponse

    @GET("3/movie/{movieId}")
    suspend fun fetchMovieDetails(@Path("movieId") movieId: Int): MovieDetailResponse
}
