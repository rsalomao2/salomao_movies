package com.salomao.movies.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salomao.movies.domain.MovieModel
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val movieList = listOf(
        MovieModel(1, "One"),
        MovieModel(2, "Two"),
        MovieModel(3, "Three"),
        MovieModel(4, "Four"),
        MovieModel(5, "Five"),
    )
    private val _movieDetailLiveData = MutableLiveData<MovieModel>()
    val movieDetailLiveData: LiveData<MovieModel> get() = _movieDetailLiveData

    fun getMovie(id: Int?) {
        viewModelScope.launch {
            if (id != null)
                getMovieById(id)?.let { movie ->
                    _movieDetailLiveData.postValue(
                        movie
                    )
                }
        }
    }

    private fun getMovieById(id: Int): MovieModel? {
        return movieList.find { it.id == id }
    }
}
