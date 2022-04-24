package com.salomao.movies.domain.di

import com.salomao.movies.domain.usecase.GetMovieDetailUseCase
import com.salomao.movies.domain.usecase.GetMovieDetailUseCaseImpl
import com.salomao.movies.presentation.moviedetail.MovieDetailViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules

fun injectMovieDetailKoin() = loadKoinModule

private val loadKoinModule by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
    )
}

private val viewModelModule: Module = module {
    viewModel {
        MovieDetailViewModel(
            getMovieDetailUseCase = get(),
            dateProvider = get(),
            stringProvider = get()
        )
    }
}

private val useCaseModule = module {
    single<GetMovieDetailUseCase> { GetMovieDetailUseCaseImpl(repository = get()) }
}
