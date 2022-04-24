package com.salomao.movies.presentation.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.salomao.movies.R
import com.salomao.movies.domain.provider.DateProvider
import com.salomao.movies.domain.provider.StringProvider
import com.salomao.movies.domain.usecase.GetMovieDetailUseCase
import com.salomao.movies.fixtures.MovieDetailModelFixture.fakeMovieDetailModel
import com.salomao.movies.fixtures.MovieUiStateFixture.fakeMovieUiState
import com.salomao.movies.fixtures.UnitTestTools.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
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
class MovieDetailViewModelTest {
    private val getMovieDetailUseCase: GetMovieDetailUseCase = mock()
    private val stringProvider: StringProvider = mock()
    private val dateProvider: DateProvider = mock()
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MovieDetailViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        setupViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun setupViewModel() {
        viewModel = MovieDetailViewModel(
            getMovieDetailUseCase = getMovieDetailUseCase,
            stringProvider = stringProvider,
            dateProvider = dateProvider
        )
    }

    @Test
    fun `whenever loadMovieDetail, it will return a UiState with Detail data processed`() =
        runBlocking {
            whenever(getMovieDetailUseCase.invoke(fakeMovieDetailModel.id)).doReturn(
                flowOf(fakeMovieDetailModel)
            )
            whenever(dateProvider.getYearFromStringDate(fakeMovieDetailModel.releaseDate)).doReturn(
                fakeMovieUiState.releaseYear
            )
            whenever(
                stringProvider.getDynamicString(
                    R.string.runtime_text,
                    fakeMovieDetailModel.runtime.toString()
                )
            ).doReturn("${fakeMovieDetailModel.runtime}")
            setupViewModel()

            viewModel.loadMovieDetail(fakeMovieDetailModel.id)

            viewModel.movieDetailFlow.getOrAwaitValue().let {
                assertEquals(fakeMovieUiState, it)
            }
        }

    @Test
    fun `whenever loadMovieDetail, and flow has exception error message should be emitted`() =
        runBlocking {
            val fakeErrorMessage = "Fake error message"
            whenever(getMovieDetailUseCase.invoke(fakeMovieDetailModel.id)).doReturn(
                flow { throw Exception(fakeErrorMessage) }
            )
            whenever(stringProvider.getString(R.string.movie_detail_load_error)).doReturn(fakeErrorMessage)
            setupViewModel()

            viewModel.loadMovieDetail(fakeMovieDetailModel.id)

            viewModel.errorMessageFlow.getOrAwaitValue().let {
                assertEquals(fakeErrorMessage, it)
            }
        }
}
