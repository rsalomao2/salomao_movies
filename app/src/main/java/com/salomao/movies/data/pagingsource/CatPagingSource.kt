package com.salomao.movies.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.salomao.movies.data.service.CatService
import com.salomao.movies.remote.MovieResponse
import java.io.IOException
import retrofit2.HttpException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class CatPagingSource(
    private val unsplashApi: CatService,
) : PagingSource<Int, MovieResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = unsplashApi.searchPhotos(position, "en-US", params.loadSize)
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