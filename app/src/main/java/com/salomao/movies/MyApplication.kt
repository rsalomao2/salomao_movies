package com.salomao.movies

import android.app.Application
import com.salomao.movies.domain.di.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this)
    }
}
