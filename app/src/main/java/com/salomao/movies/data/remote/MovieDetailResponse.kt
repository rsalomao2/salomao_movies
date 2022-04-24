package com.salomao.movies.data.remote

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val thumbnailUrl: String?,
    @SerializedName("vote_average") val score: Float,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("genres") val genreIdList: List<GenreResponse>,
    @SerializedName("overview") val overview: String?,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("runtime") val runtime: Int?,
)
