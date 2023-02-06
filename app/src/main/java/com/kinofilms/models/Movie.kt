package com.kinofilms.models

data class Movie(
    val id: Int,
    val name: String,
    val year: String,
    val rating: String,
    val imageUrl: String,
    val errorMessage: String
)

data class AllMovies(
    val data: List<Movie>?,
    var error: String = ""
)
