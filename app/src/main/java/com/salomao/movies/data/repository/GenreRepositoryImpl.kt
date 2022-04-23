package com.salomao.movies.data.repository

import com.salomao.movies.data.mapper.MovieMapper.toModel
import com.salomao.movies.data.service.GenreService
import com.salomao.movies.domain.model.GenreModel
import com.salomao.movies.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenreRepositoryImpl(
    private val service: GenreService
) : GenreRepository {
    override fun fetchGenreList(): Flow<List<GenreModel>> = flow {
        val result = service.fetchGenreList()
        val genreModelList = result.genres.map { genreResponse ->
            genreResponse.toModel()
        }
        emit(genreModelList)
    }
}
