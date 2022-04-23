package com.salomao.movies.domain.provider

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateProviderImpl : DateProvider {
    override fun getYearFromStringDate(dateString: String): String {
        return try {
            val calendarFromString = getDateStringToCalendar(dateString)
            getYear(calendarFromString)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    private fun getYear(calendar: Calendar): String {
        return calendar.get(Calendar.YEAR).toString()
    }

    private fun getDateStringToCalendar(givenDate: String): Calendar {
        val calendar: Calendar
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        try {
            calendar = Calendar.getInstance()
            calendar.time = sdf.parse(givenDate)
        } catch (e: ParseException) {
            throw e
        }
        return calendar
    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd"
    }
}
