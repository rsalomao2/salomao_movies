package com.salomao.movies.presentation.movielist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.salomao.movies.R
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.provider.DateProvider
import com.salomao.movies.domain.provider.GenreListProvider
import com.salomao.movies.domain.provider.StringProvider
import com.salomao.movies.domain.usecase.GetGenreListUseCase
import com.salomao.movies.domain.usecase.GetMovieListUseCase
import com.salomao.movies.presentation.model.MovieLitItemUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.zip

class MovieListViewModel(
    getMovieListUseCase: GetMovieListUseCase,
    getGenreListUseCase: GetGenreListUseCase,
    private val dateProvider: DateProvider,
    private val stringProvider: StringProvider,
    private val genreListProvider: GenreListProvider
) : ViewModel() {
    private val _showEmptyListViewFlow = MutableStateFlow(false)
    private val _errorMessageFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    val showEmptyListViewFlow: StateFlow<Boolean> get() = _showEmptyListViewFlow
    val errorMessageFlow: Flow<String> get() = _errorMessageFlow.filterNotNull()

    val listLiveData = getMovieListUseCase.invoke()
        .zip(getGenreListUseCase.invoke()) { pagingMovieModel, genreList ->
            pagingMovieModel.map { movieModel ->
                _showEmptyListViewFlow.emit(false)
                val genreList =
                    genreListProvider.getGenreListString(movieModel.genreIdList, genreList)
                movieModel.toMovieUiState(genreList)
            }
        }
        .catch { e: Throwable ->
            Log.e("MovieListViewModel", "${e.message}")
            _showEmptyListViewFlow.emit(true)
            _errorMessageFlow.emit(stringProvider.getString(R.string.get_movie_list_error))
        }
        .cachedIn(viewModelScope)

    private fun MovieModel.toMovieUiState(genreList: String): MovieLitItemUiState {
        return MovieLitItemUiState(
            id = id,
            name = name,
            genre = genreList,
            thumbnailUrl = thumbnailUrl,
            score = score.div(2),
            releaseYear = dateProvider.getYearFromStringDate(releaseDate)
        )
    }
}
