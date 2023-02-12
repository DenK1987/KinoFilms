package com.kinofilms.network.models

data class MovieResponse(
    val backdrop: Backdrop?,
    val budget: Budget,
    val countries: List<Country>?,
    val description: String?,
    val distributors: Distributors?,
    val externalId: ExternalId?,
    val facts: List<Fact>?,
    val fees: Fees?,
    val genres: List<Genre>?,
    val id: Int,
    val images: Images?,
    val lists: List<Any>?,
    val movieLength: Int?,
    val name: String?,
    val persons: List<PersonResponse>?,
    val poster: PosterResponse?,
    val premiere: Premiere?,
    val productionCompanies: List<ProductionCompany>?,
    val rating: RatingResponse?,
    val seasonsInfo: List<Any>?,
    val slogan: String?,
    val spokenLanguages: List<SpokenLanguage>?,
    val status: String?,
    val type: String?,
    val videos: Videos?,
    val votes: Votes?,
    val year: Int?
)

data class Backdrop(val previewUrl: String?, val url: String?)

data class Budget(val currency: String?, val value: Int?)

data class Country(val name: String?)

data class Distributors(val distributor: String?, val distributorRelease: String?)

data class ExternalId(val imdb: String?, val tmdb: Int?)

data class Fact(val value: String?)

data class Fees(val russia: Russia?, val usa: Usa?, val world: World?)
data class Russia(val currency: String?, val value: Int?)
data class Usa(val currency: String?, val value: Int?)
data class World(val currency: String?, val value: Int?)

data class Genre(val name: String?)

data class Images(val backdropsCount: Int?, val framesCount: Int?, val postersCount: Int?)

data class PersonResponse(
    val description: String?,
    val enName: String?,
    val enProfession: String?,
    val id: Int?,
    val name: String?,
    val photo: String?
)

data class Premiere(
    val bluray: String?,
    val country: String?,
    val dvd: String?,
    val russia: String?,
    val world: String?
)

data class Votes(val imdb: Int?, val kp: Int?, val tmdb: Int?)

data class ProductionCompany(val name: String?, val previewUrl: String?, val url: String?)

data class SpokenLanguage(val name: String?, val nameEn: String?)

data class Videos(val trailers: List<Trailer>?)
data class Trailer(
    val _id: String?,
    val name: String?,
    val site: String?,
    val size: Int?,
    val type: String?,
    val url: String?
)