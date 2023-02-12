package com.kinofilms.network.models

data class AllMoviesCatalogResponse(
    val docs: List<MovieCatalogResponse>?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?,
    val total: Int?
)

data class MovieCatalogResponse(
    val id: Int,
    val name: String?,
    val poster: PosterResponse?,
    val rating: RatingResponse?,
    val year: Int?
)

data class PosterResponse(
    val previewUrl: String?,
    val url: String?
)

data class RatingResponse(
    val imdb: Double?,
    val kp: Double?,
    val tmdb: Double?
)
