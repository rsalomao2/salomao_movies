package com.salomao.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.salomao.movies.data.mapper.MovieMapper.toModel
import com.salomao.movies.data.pagingsource.MoviePagingSource
import com.salomao.movies.data.service.MovieService
import com.salomao.movies.domain.Constants
import com.salomao.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val movieService: MovieService
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
        }
}