package com.salomao.movies.domain.usecase

import androidx.paging.PagingData
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCaseImpl(
    private val repository: MovieRepository
) : GetMovieListUseCase {
    override fun invoke(): Flow<PagingData<MovieModel>> = repository.fetchMovieList()
}
