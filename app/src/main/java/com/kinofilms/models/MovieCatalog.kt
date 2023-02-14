package com.kinofilms.models

data class MovieCatalog(
    val id: Int,
    val name: String,
    val year: String,
    val ratingKp: Double,
    val imageUrl: String,
    val votesKp: Int,
    val votesImdb: Int,
    val errorMessage: String
)

data class AllMoviesCatalog(
    val data: List<MovieCatalog>?,
    var error: String = ""
)
