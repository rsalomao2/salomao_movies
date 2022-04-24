package com.salomao.movies.domain.provider

interface StringProvider {
    fun getString(stringID: Int): String
    fun getDynamicString(stringID: Int, dynamicValue: String): String
}
