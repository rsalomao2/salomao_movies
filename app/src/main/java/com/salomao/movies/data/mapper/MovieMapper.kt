package com.salomao.movies.data.mapper

import com.salomao.movies.domain.Constants
import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.remote.MovieResponse

object MovieMapper {
    fun MovieResponse.toModel() = MovieModel(
        id = id,
        name = title,
        description = description,
        thumbnailUrl = "${Constants.BASE_IMAGE_URL}$thumbnailUrl",
        score = score,
        releaseDate = releaseDate,
        genreIdList = genreIdList
    )
}
