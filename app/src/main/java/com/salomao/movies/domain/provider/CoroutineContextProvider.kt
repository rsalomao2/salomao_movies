package com.salomao.movies.domain.provider

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    val IO: CoroutineContext
}
