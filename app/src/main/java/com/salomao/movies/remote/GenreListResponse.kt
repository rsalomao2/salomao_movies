package com.salomao.movies.remote

import com.google.gson.annotations.SerializedName

data class GenreListResponse(
    @SerializedName("genres") val genres: List<GenreResponse>
)

data class GenreResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
