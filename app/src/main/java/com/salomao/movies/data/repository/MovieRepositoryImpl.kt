package com.salomao.movies.data.repository

import com.salomao.movies.data.mapper.MovieMapper.toModel
import com.salomao.movies.data.service.MovieService
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState
import com.salomao.movies.domain.provider.CoroutineContextProvider
import com.salomao.movies.domain.repository.MovieRepository
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val contextProvider: CoroutineContextProvider,
) : MovieRepository {
    override suspend fun fetchMovieList(
        page: Int,
        language: String
    ): ResultState<List<MovieModel>> =
        withContext(contextProvider.IO) {
            try {
                val response = movieService.getMovieList(
                    page = page,
                    language = language
                )
                val movieModelList = response.results.map {
                    it.toModel()
                }
                ResultState.Success(movieModelList)
            } catch (e: Exception) {
                e.printStackTrace()
                ResultState.Error(e.message ?: "Unable to fetch Movie List")
            }
        }

    override suspend fun fetchMovieById(movieId: Int): ResultState<MovieModel?> =
        withContext(contextProvider.IO) {
            try {
                ResultState.Success(null)
            } catch (e: Exception) {
                e.printStackTrace()
                ResultState.Error(e.message ?: "Unable to find Movie by id: $movieId")
            }
        }
}
