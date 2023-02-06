package com.kinofilms.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun transformDouble(rating: Double): Double {
    return String.format("%.1f", rating).toDouble()

}