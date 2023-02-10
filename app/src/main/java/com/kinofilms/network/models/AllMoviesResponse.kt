package com.kinofilms.network.models

data class AllMoviesResponse(
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
    val backdrop: Backdrop?,
    val videos: Videos?,
    val genres: List<Genre>?,
    val countries: List<Country>?,
    val persons: List<Person>?,
    val premiere: Premiere?,
    val name: String?,
    val poster: Poster?,
    val rating: Rating?,
    val type: String?,
    val votes: Votes?,
    val year: Int?
)

data class ExternalId(val imdb: String?, val tmdb: Int?)

data class Poster(val previewUrl: String?, val url: String?)

data class Rating(val imdb: Double?, val kp: Double?, val tmdb: Double?)

data class Votes(val imdb: Int?, val kp: Int?, val tmdb: Int?)

data class Backdrop(val previewUrl: String?, val url: String?)

data class Videos(val trailers: List<Trailer>?)

data class Trailer(
    val _id: String?,
    val name: String?,
    val site: String?,
    val size: Int?,
    val type: String?,
    val url: String?
)

data class Genre(val name: String?)

data class Country(val name: String?)

data class Person(
    val description: String?,
    val enName: String?,
    val enProfession: String?,
    val id: Int?,
    val name: String?,
    val photo: String?
)

data class Premiere(
    val bluray: String,
    val country: String,
    val dvd: String,
    val russia: String,
    val world: String
)