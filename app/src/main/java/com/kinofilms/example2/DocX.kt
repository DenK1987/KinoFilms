package com.kinofilms.example2

data class DocX(
    val alternativeName: String,
    val description: String,
    val enName: Any,
    val externalId: ExternalIdX,
    val id: Int,
    val logo: Logo,
    val movieLength: Int,
    val name: String,
    val names: List<Name>,
    val poster: PosterX,
    val rating: RatingX,
    val releaseYears: List<ReleaseYear>,
    val shortDescription: Any,
    val type: String,
    val votes: VotesX,
    val watchability: Watchability,
    val year: Int
)