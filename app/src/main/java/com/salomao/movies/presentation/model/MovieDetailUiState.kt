package com.salomao.movies.presentation.model

data class MovieDetailUiState(
    val id: Int,
    val name: String,
    val genre: String,
    val thumbnailUrl: String,
    val score: Float,
    val releaseYear: String,
    val overview: String,
    val runTime: String,
    val homepageUrl: String,
)
