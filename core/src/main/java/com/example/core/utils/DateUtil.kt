package com.example.core.utils

import java.time.Instant.ofEpochSecond
import java.time.ZoneId.systemDefault
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtil {
    private const val PATTERN_MMMDHHMMA = "MMM d, hh:mma"

    fun convertLongToTime(time: Long): String {
        val zoneId = systemDefault() // Use system default time zone
        val zonedDateTime = ofEpochSecond(time).atZone(zoneId)
        return DateTimeFormatter.ofPattern(PATTERN_MMMDHHMMA, Locale.getDefault())
            .format(zonedDateTime)
    }
}