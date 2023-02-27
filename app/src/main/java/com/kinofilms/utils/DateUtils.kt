package com.kinofilms.utils

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

@SuppressLint("SimpleDateFormat")
fun transformDateInString(dateString: String): String {
    try {
        val parsedDate = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME)
        return parsedDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
    } catch (_: Exception) {
    }
    return ""
}

fun getAge(dateString: String): Int {
    val parsedDate = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME)

    return Period.between(
        LocalDate.of(parsedDate.year, parsedDate.month, parsedDate.dayOfMonth),
        LocalDate.now()
    ).years
}