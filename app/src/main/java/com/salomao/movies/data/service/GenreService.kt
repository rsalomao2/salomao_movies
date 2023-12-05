package com.salomao.movies.data.service

import com.salomao.movies.data.remote.GenreListResponse
import retrofit2.http.GET

interface GenreService {

    @GET("3/genre/movie/list")
    suspend fun fetchGenreList(): GenreListResponse
}
