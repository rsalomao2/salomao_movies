package com.salomao.movies.data.mapper

import com.salomao.movies.domain.model.MovieModel
import com.salomao.movies.remote.MovieResponse

object MovieMapper {
    fun MovieResponse.toModel() = MovieModel(
        id = id,
        name = title
    )
}
