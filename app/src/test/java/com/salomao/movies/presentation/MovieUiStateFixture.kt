package com.salomao.movies.presentation

import com.salomao.movies.presentation.MovieDetailModelFixture.fakeMovieDetailModel
import com.salomao.movies.presentation.model.MovieDetailUiState

/**
 * This class is used to hold the Movie UISTATE FAKE objects for unit tests.
 * Allowing it to be shared to different unit tests files
 */
object MovieUiStateFixture {
    val fakeMovieUiState = MovieDetailUiState(
        id = fakeMovieDetailModel.id,
        name = fakeMovieDetailModel.name,
        genre = "one, two",
        thumbnailUrl = fakeMovieDetailModel.thumbnailUrl,
        score = fakeMovieDetailModel.score.div(2),
        releaseYear = "2022",
        overview = fakeMovieDetailModel.overView,
        runTime = fakeMovieDetailModel.runtime.toString(),
        homepageUrl = fakeMovieDetailModel.homepageUrl
    )
}
