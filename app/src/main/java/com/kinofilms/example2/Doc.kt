package com.kinofilms.example2

data class Doc(
    val alternativeName: String?,
    val description: String?,
    val externalId: ExternalId?,
    val id: Int?,
    val name: String?,
    val poster: Poster?,
    val rating: Rating?,
    val type: String?,
    val votes: Votes?,
    val year: Int?
)