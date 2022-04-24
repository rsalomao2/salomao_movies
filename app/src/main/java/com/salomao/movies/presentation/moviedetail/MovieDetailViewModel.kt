package com.salomao.movies.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salomao.movies.R
import com.salomao.movies.domain.model.GenreModel
import com.salomao.movies.domain.model.MovieDetailModel
import com.salomao.movies.domain.provider.DateProvider
import com.salomao.movies.domain.provider.StringProvider
import com.salomao.movies.domain.usecase.GetMovieDetailUseCase
import com.salomao.movies.presentation.model.MovieDetailUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val stringProvider: StringProvider,
    private val dateProvider: DateProvider,
) : ViewModel() {

    private val _movieDetailFlow = MutableStateFlow<MovieDetailUiState?>(null)
    private val _errorMessageFlow = MutableStateFlow<String?>(null)

    val movieDetailFlow: Flow<MovieDetailUiState> get() = _movieDetailFlow.filterNotNull()
    val errorMessageFlow: Flow<String> get() = _errorMessageFlow.filterNotNull()

    fun loadMovieDetail(movieId: Int?) {
        viewModelScope.launch {
            if (movieId != null) {
                getMovieDetailUseCase.invoke(movieId).map {
                    it.toMovieUiState(it.genreIdList)
                }.catch { e: Throwable ->
                    e.printStackTrace()
                    Log.e(TAG, "${e.message}")
                    _errorMessageFlow.emit(stringProvider.getString(R.string.movie_detail_load_error))
                }.collect { uiState ->
                    _movieDetailFlow.emit(uiState)
                }
            }
        }
    }

    private fun MovieDetailModel.toMovieUiState(genreIdList: List<GenreModel>): MovieDetailUiState {
        val genreListString = getGenreListString(genreIdList)
        return MovieDetailUiState(
            id = id,
            name = name,
            genre = genreListString,
            thumbnailUrl = thumbnailUrl,
            score = score.div(2),
            releaseYear = dateProvider.getYearFromStringDate(releaseDate),
            overview = overView,
            runTime = stringProvider.getDynamicString(R.string.runtime_text, runtime.toString()),
            homepageUrl = homepageUrl
        )
    }

    private fun getGenreListString(genreIdList: List<GenreModel>) =
        genreIdList
            .filter { it.name.isNotBlank() }
            .map { it.name }
            .joinToString()

    companion object {
        private const val TAG = "MovieDetailViewModel"
    }
}
