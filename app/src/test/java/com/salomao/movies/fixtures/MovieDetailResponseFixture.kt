package com.salomao.movies.fixtures

import com.salomao.movies.data.remote.GenreResponse
import com.salomao.movies.data.remote.MovieDetailResponse
import com.salomao.movies.fixtures.MovieDetailModelFixture.fakeMovieDetailModel

object MovieDetailResponseFixture {

    val fakeMovieDetailResponse = MovieDetailResponse(
        id = fakeMovieDetailModel.id,
        title = fakeMovieDetailModel.name,
        thumbnailUrl = "/imageLink",
        score = fakeMovieDetailModel.score,
        releaseDate = fakeMovieDetailModel.releaseDate,
        genreIdList = getGenreResponse(),
        overview = fakeMovieDetailModel.overView,
        homepage = fakeMovieDetailModel.homepageUrl,
        runtime = fakeMovieDetailModel.runtime
    )

    private fun getGenreResponse(): List<GenreResponse> {
        return fakeMovieDetailModel.genreIdList.map {
            GenreResponse(
                id = it.id,
                name = it.name
            )
        }
    }
}
