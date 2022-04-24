package com.salomao.movies.domain.usecase

import com.salomao.movies.domain.model.MovieDetailModel
import com.salomao.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailUseCaseImpl(
    private val repository: MovieRepository
) : GetMovieDetailUseCase {
    override fun invoke(movieId: Int): Flow<MovieDetailModel> =
        repository.fetchMovieDetails(movieId)
}
