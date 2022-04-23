package com.salomao.movies.domain.di

import com.salomao.movies.data.repository.GenreRepositoryImpl
import com.salomao.movies.data.repository.MovieRepositoryImpl
import com.salomao.movies.data.service.GenreService
import com.salomao.movies.data.service.MovieService
import com.salomao.movies.domain.builder.createRetrofit
import com.salomao.movies.domain.repository.GenreRepository
import com.salomao.movies.domain.repository.MovieRepository
import com.salomao.movies.domain.usecase.GetGenreListUseCase
import com.salomao.movies.domain.usecase.GetGenreListUseCaseImpl
import com.salomao.movies.domain.usecase.GetMovieListUseCase
import com.salomao.movies.domain.usecase.GetMovieListUseCaseImpl
import com.salomao.movies.presentation.movielist.MovieListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules

fun injectMovieListKoin() = loadKoinModule

private val loadKoinModule by lazy {
    loadKoinModules(
        serviceModule,
        repositoryModule,
        viewModelModule,
        useCaseModule
    )
}

private val serviceModule = module {
    single { createRetrofit<MovieService>(okHttpClient = get()) }
    single { createRetrofit<GenreService>(okHttpClient = get()) }
}

private val viewModelModule: Module = module {
    viewModel {
        MovieListViewModel(
            getMovieListUseCase = get(),
            getGenreListUseCase = get(),
            dateProvider = get()
        )
    }
}

private val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(movieService = get()) }
    single<GenreRepository> { GenreRepositoryImpl(service = get()) }
}

private val useCaseModule = module {
    single<GetMovieListUseCase> { GetMovieListUseCaseImpl(repository = get()) }
    single<GetGenreListUseCase> { GetGenreListUseCaseImpl(repository = get()) }
}
