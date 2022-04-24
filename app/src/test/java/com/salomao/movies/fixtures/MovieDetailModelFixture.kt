package com.salomao.movies.fixtures

import com.salomao.movies.domain.Constants
import com.salomao.movies.domain.model.MovieDetailModel
import com.salomao.movies.fixtures.GenreModelFixture.fakeGenreModel

/**
 * This class is used to hold the Movie DETAIL Model FAKE objects for unit tests.
 * Allowing it to be shared to different unit tests files
 */
object MovieDetailModelFixture {
    val fakeMovieDetailModel = MovieDetailModel(
        id = 123,
        name = "FakeMovieUiState Name",
        thumbnailUrl = "${Constants.BASE_IMAGE_URL}/imageLink",
        score = 10F,
        homepageUrl = "www.imdb.com.br",
        releaseDate = "2022/05/05",
        genreIdList = listOf(fakeGenreModel.copy(1, "one"), fakeGenreModel.copy(2, "two")),
        overView = "This is a fake overview from a specific movie and used only for unit test only",
        runtime = 170
    )
}
