package com.salomao.movies.domain.repository

import com.salomao.movies.domain.model.GenreModel
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun fetchGenreList(): Flow<List<GenreModel>>
}
