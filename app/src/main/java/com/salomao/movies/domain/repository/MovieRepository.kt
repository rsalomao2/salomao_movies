package com.salomao.movies.domain.repository

import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState

interface MovieRepository {
    suspend fun fetchMovieList(): ResultState<List<MovieModel>>
    suspend fun fetchMovieById(movieId: Int): ResultState<MovieModel?>
}
