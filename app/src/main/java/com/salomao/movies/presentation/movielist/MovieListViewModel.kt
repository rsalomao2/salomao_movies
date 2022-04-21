package com.salomao.movies.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.salomao.movies.R
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.domain.model.ResultState
import com.salomao.movies.domain.provider.StringProvider
import com.salomao.movies.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val stringProvider: StringProvider
) : ViewModel() {
    private val _movieListLiveData = MutableLiveData<List<MovieModel>>()
    private val _errorMessageLiveData = MutableLiveData<String>()
    private val _showLoadingLiveData = MutableLiveData<Boolean>()

    val movieListLiveData: LiveData<List<MovieModel>> get() = _movieListLiveData
    val errorMessageLiveData: LiveData<String> get() = _errorMessageLiveData
    val showLoadingLiveData: LiveData<Boolean> get() = _showLoadingLiveData
    val showEmptyLiveData: LiveData<Boolean> = movieListLiveData.switchMap { movieList ->
        liveData { emit(movieList.isEmpty()) }
    }

    fun getMovieList() {
        viewModelScope.launch {
            _showLoadingLiveData.postValue(true)
            when (val result = getMovieListUseCase.invoke()) {
                is ResultState.Success -> {
                    val movieList = result.response
                    if (movieList.isNotEmpty())
                        _movieListLiveData.postValue(movieList)
                }
                is ResultState.Error -> {
                    val errorMessage = stringProvider.getString(R.string.get_movie_list_erro)
                    _errorMessageLiveData.postValue(errorMessage)
                    _movieListLiveData.postValue(emptyList())
                }
            }
            _showLoadingLiveData.postValue(false)
        }
    }
}
