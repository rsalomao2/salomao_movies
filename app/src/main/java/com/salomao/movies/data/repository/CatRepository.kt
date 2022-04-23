package com.salomao.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.salomao.movies.data.mapper.MovieMapper.toModel
import com.salomao.movies.data.pagingsource.CatPagingSource
import com.salomao.movies.data.service.CatService
import kotlinx.coroutines.flow.map

class CatRepository(
    private val unsplashApi: CatService
) {

    fun getSearchResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CatPagingSource(unsplashApi) }
        ).flow.map {
            it.map { it.toModel() }
        }
}