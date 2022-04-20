package com.salomao.movies.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salomao.movies.domain.MovieModel
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val _movieListLiveData = MutableLiveData<List<MovieModel>>()
    val movieListLiveData: LiveData<List<MovieModel>> get() = _movieListLiveData

    fun getMovieList() {
        viewModelScope.launch {
            _movieListLiveData.postValue(
                listOf(
                    MovieModel(1, "One"),
                    MovieModel(2, "Two"),
                    MovieModel(3, "Three"),
                    MovieModel(4, "Four"),
                    MovieModel(5, "Five"),
                )
            )
        }
    }
}
