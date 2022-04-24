package com.salomao.movies.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.salomao.movies.data.service.MovieService
import com.salomao.movies.domain.repository.MovieRepository
import com.salomao.movies.fixtures.MovieDetailModelFixture.fakeMovieDetailModel
import com.salomao.movies.fixtures.MovieDetailResponseFixture.fakeMovieDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()
    private val movieService: MovieService = mock()

    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = MovieRepositoryImpl(
            movieService = movieService,
            dispatcher = Dispatchers.IO
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchMovieDetails() = runBlocking {
        val movieId = fakeMovieDetailModel.id
        whenever(movieService.fetchMovieDetails(movieId)).doReturn(
            fakeMovieDetailResponse
        )

        repository.fetchMovieDetails(movieId).collectLatest {
            assertEquals(fakeMovieDetailModel, it)
        }
    }
}
