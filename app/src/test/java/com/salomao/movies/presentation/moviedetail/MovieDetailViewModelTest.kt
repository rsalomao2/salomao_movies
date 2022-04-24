package com.salomao.movies.presentation.moviedetail

import com.salomao.movies.domain.provider.DateProvider
import com.salomao.movies.domain.provider.StringProvider
import com.salomao.movies.domain.usecase.GetMovieDetailUseCase
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class MovieDetailViewModelTest {
    private val getMovieDetailUseCase: GetMovieDetailUseCase = mock()
    private val stringProvider: StringProvider = mock()
    private val dateProvider: DateProvider = mock()

    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(
            getMovieDetailUseCase = getMovieDetailUseCase,
            stringProvider = stringProvider,
            dateProvider = dateProvider
        )
    }

    @Test
    fun getMovieDetailFlow() {
        assertNotNull(viewModel)
    }

    @Test
    fun getErrorMessageFlow() {
    }
}