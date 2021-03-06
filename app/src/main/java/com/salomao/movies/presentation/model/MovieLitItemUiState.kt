package com.salomao.movies.presentation.model

data class MovieLitItemUiState(
    val id: Int,
    val name: String,
    val genre: String,
    val thumbnailUrl: String,
    val score: Float,
    val releaseYear: String
)
