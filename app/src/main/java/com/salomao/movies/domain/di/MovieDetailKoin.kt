package com.salomao.movies.domain.di

import com.salomao.movies.data.repository.MovieRepositoryImpl
import com.salomao.movies.domain.repository.MovieRepository
import com.salomao.movies.domain.usecase.GetMovieByIdUseCase
import com.salomao.movies.domain.usecase.GetMovieByIdUseCaseImpl
import com.salomao.movies.presentation.movielist.MovieListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules

fun injectMovieDetailKoin() = loadKoinModule

private val loadKoinModule by lazy {
    loadKoinModules(
        repositoryModule,
        viewModelModule,
        useCaseModule
    )
}

private val viewModelModule: Module = module {
    viewModel {
        MovieListViewModel(
            getMovieListUseCase = get()
        )
    }
}

private val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(contextProvider = get()) }
}

private val useCaseModule = module {
    single<GetMovieByIdUseCase> { GetMovieByIdUseCaseImpl(movieRepository = get()) }
}
