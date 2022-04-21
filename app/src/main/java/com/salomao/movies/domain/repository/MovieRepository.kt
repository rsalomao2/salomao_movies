package com.salomao.movies.domain.repository

import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState

interface MovieRepository {
    suspend fun fetchMovieList(
        page: Int,
        language: String = DEFAULT_LANGUAGE
    ): ResultState<List<MovieModel>>

    suspend fun fetchMovieById(movieId: Int): ResultState<MovieModel?>

    private companion object {
        const val DEFAULT_LANGUAGE = "en-US"
    }
}
