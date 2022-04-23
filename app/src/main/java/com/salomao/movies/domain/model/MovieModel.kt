package com.salomao.movies.domain.model

data class MovieModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val score: Float,
    val releaseDate: String,
    val genreIdList: List<Int>
)
