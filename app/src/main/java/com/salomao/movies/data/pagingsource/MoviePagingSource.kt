package com.salomao.movies.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.salomao.movies.data.service.MovieService
import com.salomao.movies.domain.Constants
import com.salomao.movies.data.remote.MovieResponse
import java.io.IOException
import retrofit2.HttpException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val movieService: MovieService,
) : PagingSource<Int, MovieResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response =
                movieService.fetchMovieList(position, Constants.DEFAULT_LANGUAGE, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return null
    }
}