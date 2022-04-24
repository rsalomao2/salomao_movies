package com.salomao.movies.domain.provider

import android.content.Context

class StringProviderImpl(private val context: Context) : StringProvider {
    override fun getString(stringID: Int): String {
        return context.getString(stringID)
    }

    override fun getDynamicString(stringID: Int, dynamicValue: String): String {
        return String.format(getString(stringID), dynamicValue)
    }
}
