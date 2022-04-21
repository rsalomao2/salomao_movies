package com.salomao.movies.domain.di

import android.app.Application
import com.salomao.movies.domain.builder.createOkHttpClient
import com.salomao.movies.domain.provider.CoroutineContextProvider
import com.salomao.movies.domain.provider.CoroutineContextProviderImpl
import com.salomao.movies.domain.provider.StringProvider
import com.salomao.movies.domain.provider.StringProviderImpl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.with
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext

lateinit var okHttpClient: OkHttpClient

fun startKoin(myApplication: Application) {
    StandAloneContext.startKoin(
        listOf(
            networkModule,
            providerModule
        )
    ) with myApplication

    okHttpClient = createOkHttpClient()
}

private val networkModule = module {
    single { okHttpClient }
}

private val providerModule = module {
    single<StringProvider> { StringProviderImpl(androidContext()) }
    single<CoroutineContextProvider> { CoroutineContextProviderImpl() }
}
