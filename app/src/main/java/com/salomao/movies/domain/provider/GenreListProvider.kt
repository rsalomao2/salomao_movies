package com.salomao.movies.domain.provider

import com.salomao.movies.domain.model.GenreModel

interface GenreListProvider {

    fun getGenreListString(
        genreIdList: List<Int>,
        genreList: List<GenreModel>
    ): String
}
