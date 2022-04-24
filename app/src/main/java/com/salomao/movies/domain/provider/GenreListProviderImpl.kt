package com.salomao.movies.domain.provider

import com.salomao.movies.domain.Constants
import com.salomao.movies.domain.model.GenreModel

class GenreListProviderImpl : GenreListProvider {
    override fun getGenreListString(
        genreIdList: List<Int>,
        genreList: List<GenreModel>
    ): String {
        val movieGenreListName = genreList.filter { genre ->
            genreIdList.contains(genre.id)
        }.map { genreFound -> genreFound.name }
        return movieGenreListName
            .filterNot { it.isBlank() }
            .joinToString(Constants.GENRE_SEPARATOR)
    }
}
