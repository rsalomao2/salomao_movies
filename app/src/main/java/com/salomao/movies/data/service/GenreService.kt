package com.salomao.movies.data.service

import com.salomao.movies.BuildConfig
import com.salomao.movies.data.remote.GenreListResponse
import retrofit2.http.GET

interface GenreService {

    @GET("${BuildConfig.MOVIE_DB_API_VERSION}/genre/movie/list")
    suspend fun fetchGenreList(): GenreListResponse
}
