package com.salomao.movies.data.remote

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val results: List<MovieResponse>
)

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val thumbnailUrl: String?,
    @SerializedName("vote_average") val score: Float,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("genre_ids") val genreIdList: List<Int>,
)
