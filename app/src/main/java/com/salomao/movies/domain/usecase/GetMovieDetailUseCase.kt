package com.salomao.movies.domain.usecase

import com.salomao.movies.domain.model.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailUseCase {
    fun invoke(movieId: Int): Flow<MovieDetailModel>
}
