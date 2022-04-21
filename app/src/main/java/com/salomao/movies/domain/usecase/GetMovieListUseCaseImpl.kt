package com.salomao.movies.domain.usecase

import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState
import com.salomao.movies.domain.repository.MovieRepository

class GetMovieListUseCaseImpl(
    private val repository: MovieRepository
) : GetMovieListUseCase {
    private var page = 0
    override suspend fun invoke(): ResultState<List<MovieModel>> {
        return repository.fetchMovieList(page++)
    }
}
