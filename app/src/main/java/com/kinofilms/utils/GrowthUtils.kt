package com.kinofilms.utils

fun transformGrowthPerson(growth: Int): String {

    val result = String.format("%.2f", growth * 0.01).replace(",", ".")

    return "$result ${"м"}"
}