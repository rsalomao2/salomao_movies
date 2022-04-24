package com.salomao.movies.data.mapper

import com.salomao.movies.data.remote.GenreResponse
import com.salomao.movies.data.remote.MovieDetailResponse
import com.salomao.movies.data.remote.MovieResponse
import com.salomao.movies.domain.Constants
import com.salomao.movies.domain.model.GenreModel
import com.salomao.movies.domain.model.MovieDetailModel
import com.salomao.movies.domain.model.MovieModel

object MovieMapper {
    fun MovieResponse.toModel() = MovieModel(
        id = id,
        name = title,
        thumbnailUrl = "${Constants.BASE_IMAGE_URL}$thumbnailUrl",
        score = score,
        releaseDate = releaseDate,
        genreIdList = genreIdList
    )

    fun GenreResponse.toModel() = GenreModel(
        id = id,
        name = name
    )

    fun MovieDetailResponse.toModel() = MovieDetailModel(
        id = id,
        name = title,
        thumbnailUrl = "${Constants.BASE_IMAGE_URL}$thumbnailUrl",
        score = score,
        releaseDate = releaseDate,
        genreIdList = genreIdList.map { it.toModel() },
        homepageUrl = homepage,
        overView = overview ?: "",
        runtime = runtime ?: 0
    )
}
