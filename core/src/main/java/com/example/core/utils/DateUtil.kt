package com.example.core.utils

import com.example.core.utils.Constants.PATTERN_MMMDHHMMA
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {
    fun convertLongToDate(time: Long): Date = Date(time)

    fun convertDateToString(date: Date): String =
        SimpleDateFormat(PATTERN_MMMDHHMMA, Locale.getDefault()).format(date)
}