package com.salomao.movies.data.service

import com.salomao.movies.BuildConfig
import com.salomao.movies.remote.CatListResponse
import com.salomao.movies.remote.MovieListResponse
import com.salomao.movies.remote.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatService {

    companion object {
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }

    @GET("${BuildConfig.MOVIE_DB_API_VERSION}/movie/popular")
    suspend fun searchPhotos(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("per_page") perPage: Int
    ): MovieListResponse
}