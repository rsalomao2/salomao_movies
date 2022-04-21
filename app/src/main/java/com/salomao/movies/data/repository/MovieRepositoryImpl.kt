package com.salomao.movies.data.repository

import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState
import com.salomao.movies.domain.provider.CoroutineContextProvider
import com.salomao.movies.domain.repository.MovieRepository
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val contextProvider: CoroutineContextProvider,
) : MovieRepository {
    override suspend fun fetchMovieList(): ResultState<List<MovieModel>> =
        withContext(contextProvider.IO) {
            try {
                val movieList = getFakeList()
                ResultState.Success(movieList)
            } catch (e: Exception) {
                ResultState.Error(e.message ?: "Unable to fetch Movie List")
            }
        }

    override suspend fun fetchMovieById(movieId: Int): ResultState<MovieModel?> =
        withContext(contextProvider.IO) {
            try {
                val movieList = getFakeList()
                val movieById = movieList.firstOrNull { it.id == movieId }
                ResultState.Success(movieById)
            } catch (e: Exception) {
                ResultState.Error(e.message ?: "Unable to find Movie by id: $movieId")
            }
        }

    private fun getFakeList(): List<MovieModel> {
        val list = mutableListOf<MovieModel>()
        for (i in 1..100) {
            list.add(MovieModel(i, "Item $i"))
        }
        return list
    }
}
