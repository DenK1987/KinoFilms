package com.kinofilms.models

data class Movie(
    val id: Int,
    val name: String,
    val alternativeName: String,
    val year: String,
    val ratingKp: Double,
    val ratingImdb: Double,
//    val ratingTmdb: String,
    val imageBackdropUrl: String,
    val description: String,
    val movieLength: Int,
    val genres: List<String>,
    val countries: List<String>,
    val worldPremiere: String,
    val premiereInRussia: String,
//    val trailers: List<Trailer>,
    val actors: List<Person>,
    val filmCrew: List<Person>,
//    val sequelsAndPrequels: List<SequelsAndPrequel>,
    val errorMessage: String
)

data class AllMovies(
    val data: List<Movie>?,
    var error: String = ""
)

data class Trailer(
    val name: String,
    val url: String
)

data class Person(
    val description: String,
    val enName: String,
    val enProfession: String,
    val id: Int,
    val name: String,
    val photo: String
)

data class SequelsAndPrequel(
    val _id: String,
    val alternativeName: String,
    val id: Int,
    val name: String,
    val type: String
)