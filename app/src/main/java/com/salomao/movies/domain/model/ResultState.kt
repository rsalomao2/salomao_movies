package com.salomao.movies.domain.model

sealed class ResultState<out T> {
    class Success<T>(val response: T) : ResultState<T>()
    class Error(val responseError: String) : ResultState<Nothing>()
}
