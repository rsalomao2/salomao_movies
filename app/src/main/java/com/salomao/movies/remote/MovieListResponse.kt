package com.salomao.movies.remote

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val results: List<MovieResponse>
)

data class MovieResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val description: String,
    @SerializedName("poster_path") val thumbnailUrl: String,
    @SerializedName("vote_average") val score: Float,
)
