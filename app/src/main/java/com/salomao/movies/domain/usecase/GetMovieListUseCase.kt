package com.salomao.movies.domain.usecase

import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState

interface GetMovieListUseCase {
    suspend fun invoke(): ResultState<List<MovieModel>>
}
