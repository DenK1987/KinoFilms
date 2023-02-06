package com.kinofilms.network.models.moviesbytype

data class MoviesByType(
    val docs: List<MovieResponse>?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?,
    val total: Int?
)

data class MovieResponse(
    val description: String?,
    val externalId: ExternalId?,
    val id: Int,
    val movieLength: Int?,
    val name: String?,
    val poster: Poster?,
    val rating: Rating?,
    val type: String?,
    val votes: Votes?,
    val year: Int?
)

data class ExternalId(
    val imdb: String?,
    val tmdb: Int?
)

data class Poster(
    val previewUrl: String?,
    val url: String?
)

data class Rating(
    val imdb: Double?,
    val kp: Double?,
    val tmdb: Double?
)

data class Votes(
    val imdb: Int?,
    val kp: Int?,
    val tmdb: Int?
)