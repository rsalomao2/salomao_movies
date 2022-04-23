package com.salomao.movies.presentation.movielist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.salomao.movies.domain.model.GenreModel
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.provider.DateProvider
import com.salomao.movies.domain.usecase.GetGenreListUseCase
import com.salomao.movies.domain.usecase.GetMovieListUseCase
import com.salomao.movies.presentation.model.MovieLitItemUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.zip

class MovieListViewModel(
    getMovieListUseCase: GetMovieListUseCase,
    getGenreListUseCase: GetGenreListUseCase,
    private val dateProvider: DateProvider
) : ViewModel() {

    val listLiveData = getMovieListUseCase.invoke()
        .zip(getGenreListUseCase.invoke()) { pagingMovieModel, genreList ->
            pagingMovieModel.map { movieModel ->
                val genreList = getGenreListDescription(movieModel, genreList)
                movieModel.toMovieUiState(genreList)
            }
        }
        .catch { e: Throwable ->
            Log.d("MovieListViewModel", "${e.message}")
        }
        .cachedIn(viewModelScope)

    private fun getGenreListDescription(
        movieModel: MovieModel,
        genreList: List<GenreModel>
    ): String {
        val movieGenreListName = genreList.filter { genre ->
            movieModel.genreIdList.contains(genre.id)
        }.map { genreFound -> genreFound.name }
        return movieGenreListName.joinToString(" / ")
    }

    private fun MovieModel.toMovieUiState(genreList: String): MovieLitItemUiState {
        return MovieLitItemUiState(
            id = id,
            name = name,
            genre = genreList,
            thumbnailUrl = thumbnailUrl,
            score = score.div(2),
            releaseDate = dateProvider.getYearFromStringDate(releaseDate)
        )
    }
}
