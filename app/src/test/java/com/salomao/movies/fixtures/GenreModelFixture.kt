package com.salomao.movies.fixtures

import com.salomao.movies.domain.model.GenreModel
import com.salomao.movies.domain.model.MovieDetailModel
import com.salomao.movies.presentation.model.MovieDetailUiState

/**
 * This class is used to hold the GENRE Model FAKE objects for unit tests.
 * Allowing it to be shared to different unit tests files
 */
object GenreModelFixture {
    val fakeGenreModel = GenreModel(
        id = 123,
        name = "fakeGenreModel Name",
    )
}
