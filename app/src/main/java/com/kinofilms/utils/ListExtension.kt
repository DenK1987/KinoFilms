package com.kinofilms.utils

fun List<String>.string(): String {

    val result = StringBuilder()

    this.forEachIndexed() { index, value ->
        result.append(if (index.plus(1) == this.size) value else "$value, ")
    }

    return result.toString()
}