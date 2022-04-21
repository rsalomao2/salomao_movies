package com.salomao.movies.domain.usecase

import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState
import com.salomao.movies.domain.repository.MovieRepository

class GetMovieByIdUseCaseImpl(
    private val movieRepository: MovieRepository
) : GetMovieByIdUseCase {
    override suspend fun invoke(movieId: Int): ResultState<MovieModel?> {
        return movieRepository.fetchMovieById(movieId)
    }
}