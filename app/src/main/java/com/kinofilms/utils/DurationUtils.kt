package com.kinofilms.utils

import android.util.Log
import java.math.RoundingMode

fun transformDurationMovie(durationInMinutes: Int): String {

    val desiredHours =
        (durationInMinutes / 60).toBigDecimal().setScale(1, RoundingMode.HALF_DOWN).toInt()

    val desiredMinutes = durationInMinutes - desiredHours * 60

    return if (desiredHours != 0) "$desiredHours ${"ч."} $desiredMinutes ${"мин."}"
    else "$desiredMinutes ${"мин."}"

}