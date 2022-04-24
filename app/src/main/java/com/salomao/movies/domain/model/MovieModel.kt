package com.salomao.movies.domain.model

data class MovieModel(
    val id: Int,
    val name: String,
    val thumbnailUrl: String,
    val score: Float,
    val releaseDate: String,
    val genreIdList: List<Int>
)
