package com.salomao.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.salomao.movies.data.mapper.MovieMapper.toModel
import com.salomao.movies.data.pagingsource.MoviePagingSource
import com.salomao.movies.data.service.MovieService
import com.salomao.movies.domain.Constants
import com.salomao.movies.domain.model.MovieDetailModel
import com.salomao.movies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override fun fetchMovieList() =
        Pager(
            config = PagingConfig(
                pageSize = Constants.PAGING_SIZE,
                maxSize = Constants.PAGING_SIZE_MAX,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(movieService) }
        ).flow.map { pagingData ->
            pagingData.map { movieResponse ->
                movieResponse.toModel()
            }
        }.flowOn(dispatcher)

    override fun fetchMovieDetails(movieId: Int): Flow<MovieDetailModel> = flow {
        val movieDetailResponse = movieService.fetchMovieDetails(movieId)
        val movieDetailModel = movieDetailResponse.toModel()
        emit(movieDetailModel)
    }.flowOn(dispatcher)
}
