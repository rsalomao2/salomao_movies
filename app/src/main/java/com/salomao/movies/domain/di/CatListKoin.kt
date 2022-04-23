package com.salomao.movies.domain.di

import com.salomao.movies.data.repository.CatRepository
import com.salomao.movies.data.service.CatService
import com.salomao.movies.domain.builder.createRetrofit
import com.salomao.movies.presentation.delete.CatListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext

fun injectCatListKoin() = loadKoinModule

private val loadKoinModule by lazy {
    StandAloneContext.loadKoinModules(
        serviceModule,
        repositoryModule,
        viewModelModule,
    )
}

private val serviceModule = module {
    single { createRetrofit<CatService>(okHttpClient = get()) }
}

private val viewModelModule: Module = module {
    viewModel {
        CatListViewModel(
            repository = get()
        )
    }
}

private val repositoryModule = module {
    single { CatRepository(get()) }
}
