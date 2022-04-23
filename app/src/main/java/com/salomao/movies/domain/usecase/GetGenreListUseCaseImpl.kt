package com.salomao.movies.domain.usecase

import com.salomao.movies.domain.model.GenreModel
import com.salomao.movies.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class GetGenreListUseCaseImpl(
    private val repository: GenreRepository
) : GetGenreListUseCase {
    override fun invoke(): Flow<List<GenreModel>> = repository.fetchGenreList()
}
