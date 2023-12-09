package com.github.cesar1287.cstv.extensions

import android.text.format.DateFormat
import android.text.format.DateUtils.isToday
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

private const val ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val UTC_TIME_ZONE = "UTC"
private const val MATCH_CARD_DATE_FORMAT = "dd.MM HH:mm"
private const val MATCH_CARD_DATE_FORMAT_HOUR_ONLY = "HH:mm"
private const val DAY_OF_WEEK_NAME = "EEE"

fun String.getPrettyDate(): Pair<Boolean, String> {
    //UTC Date - Default
    val utcSimpleDateFormat = SimpleDateFormat(ISO8601_DATE_FORMAT, Locale.getDefault())
    utcSimpleDateFormat.timeZone = TimeZone.getTimeZone(UTC_TIME_ZONE)

    val userDate = utcSimpleDateFormat.parse(this)

    val matchCardSimpleDateFormat = SimpleDateFormat(MATCH_CARD_DATE_FORMAT, Locale.getDefault())
    val matchCardHourOnlySimpleDateFormat =
        SimpleDateFormat(MATCH_CARD_DATE_FORMAT_HOUR_ONLY, Locale.getDefault())

    if (userDate == null) {
        return Pair(false, "")
    }

    return if (isToday(userDate.time)) {
        Pair(true, matchCardHourOnlySimpleDateFormat.format(userDate))
    } else if (isThisWeek(userDate)) {
        Pair(
            false,
            "${
                DateFormat.format(
                    DAY_OF_WEEK_NAME,
                    userDate
                )
            }, ${matchCardHourOnlySimpleDateFormat.format(userDate)}"
        )
    } else {
        Pair(
            false,
            matchCardSimpleDateFormat.format(userDate)
        )
    }
}

private fun isThisWeek(userDate: Date?): Boolean {
    return userDate?.let {
        val currentDayCalendar = Calendar.getInstance()
        val userDateCalendar = Calendar.getInstance()
        userDateCalendar.time = userDate

        currentDayCalendar.get(Calendar.WEEK_OF_YEAR) ==
                userDateCalendar.get(Calendar.WEEK_OF_YEAR)
    } ?: false
}