package com.salomao.movies.presentation

import com.salomao.movies.domain.model.MovieDetailModel
import com.salomao.movies.presentation.GenreModelFixture.fakeGenreModel

/**
 * This class is used to hold the Movie DETAIL Model FAKE objects for unit tests.
 * Allowing it to be shared to different unit tests files
 */
object MovieDetailModelFixture {
    val fakeMovieDetailModel = MovieDetailModel(
        id = 123,
        name = "FakeMovieUiState Name",
        thumbnailUrl = "www.thubnail.com/mylink",
        score = 10F,
        homepageUrl = "www.imdb.com.br",
        releaseDate = "2022/05/05",
        genreIdList = listOf(fakeGenreModel.copy(1, "one"), fakeGenreModel.copy(2, "two")),
        overView = "This is a fake overview from a specific movie and used only for unit test only",
        runtime = 170
    )
}
