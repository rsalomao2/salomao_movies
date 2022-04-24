package com.salomao.movies.domain.model

data class MovieDetailModel(
    val id: Int,
    val name: String,
    val thumbnailUrl: String,
    val score: Float,
    val releaseDate: String,
    val genreIdList: List<GenreModel>,
    val overView: String,
    val runtime: Int,
    val homepageUrl: String
)
