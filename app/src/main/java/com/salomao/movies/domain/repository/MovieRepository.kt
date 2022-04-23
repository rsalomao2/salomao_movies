package com.salomao.movies.domain.repository

import androidx.paging.PagingData
import com.salomao.movies.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovieList(): Flow<PagingData<MovieModel>>
}
