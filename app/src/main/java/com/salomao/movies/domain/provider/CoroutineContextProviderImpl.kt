package com.salomao.movies.domain.provider

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

class CoroutineContextProviderImpl : CoroutineContextProvider {
    override val IO: CoroutineContext by lazy { Dispatchers.IO }
}
