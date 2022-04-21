package com.salomao.movies.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState
import com.salomao.movies.domain.usecase.GetMovieByIdUseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {
    private val _movieDetailLiveData = MutableLiveData<MovieModel>()
    val movieDetailLiveData: LiveData<MovieModel> get() = _movieDetailLiveData

    fun getMovie(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                when (val result = getMovieByIdUseCase.invoke(id)) {
                    is ResultState.Success -> {
                        result.response?.let { movieById ->
                            _movieDetailLiveData.postValue(movieById)
                        }
                    }
                    is ResultState.Error -> TODO("getMovieList is not implementing ResultState.Error yet")
                }
            }
        }
    }
}
