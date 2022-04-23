package com.salomao.movies.domain.usecase

import androidx.paging.PagingData
import com.salomao.movies.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface GetMovieListUseCase {
    suspend fun invoke(): Flow<PagingData<MovieModel>>
}
