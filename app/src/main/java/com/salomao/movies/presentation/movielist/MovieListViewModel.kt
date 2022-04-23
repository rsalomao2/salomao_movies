package com.salomao.movies.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.provider.DateProvider
import com.salomao.movies.domain.repository.MovieRepository
import com.salomao.movies.domain.usecase.GetMovieListUseCase
import com.salomao.movies.presentation.model.MovieLitItemUiState
import kotlinx.coroutines.flow.map

class MovieListViewModel(
    getMovieListUseCase: GetMovieListUseCase,
    private val dateProvider: DateProvider
) : ViewModel() {

    val listLiveData = getMovieListUseCase.invoke().map { pagingData ->
        pagingData.map { movieModel ->
            movieModel.toMovieUiState()
        }
    }.cachedIn(viewModelScope)

    private fun MovieModel.toMovieUiState(): MovieLitItemUiState {
        return MovieLitItemUiState(
            id = id,
            name = name,
            genre = "Genre",
            thumbnailUrl = thumbnailUrl,
            score = score.div(2),
            releaseDate = dateProvider.getYearFromStringDate(releaseDate)
        )
    }
}
