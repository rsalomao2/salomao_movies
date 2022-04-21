package com.salomao.movies.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState
import com.salomao.movies.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {
    private val _movieListLiveData = MutableLiveData<List<MovieModel>>()
    val movieListLiveData: LiveData<List<MovieModel>> get() = _movieListLiveData

    fun getMovieList() {
        viewModelScope.launch {
            when (val result = getMovieListUseCase.invoke()) {
                is ResultState.Success -> {
                    val movieList = result.response
                    if (movieList.isNotEmpty())
                        _movieListLiveData.postValue(movieList)
                }
                is ResultState.Error -> TODO("getMovieList is not implementing ResultState.Error yet")
            }
        }
    }
}
