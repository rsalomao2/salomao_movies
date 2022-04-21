package com.salomao.movies.domain.usecase

import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState

interface GetMovieByIdUseCase {
    suspend fun invoke(movieId: Int): ResultState<MovieModel?>
}
