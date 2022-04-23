package com.salomao.movies.domain.provider


interface DateProvider {
    fun getYearFromStringDate(dateString: String): String
}
