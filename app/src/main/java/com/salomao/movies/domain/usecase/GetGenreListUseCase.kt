package com.salomao.movies.domain.usecase

import com.salomao.movies.domain.model.GenreModel
import kotlinx.coroutines.flow.Flow

interface GetGenreListUseCase {
    fun invoke(): Flow<List<GenreModel>>
}
