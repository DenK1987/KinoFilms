package com.kinofilms.models

data class Movie(
    val id: Int,
    val name: String,
    val year: String,
    val ratingKp: String,
    val ratingImdb: String,
    val ratingTmdb: String,
    val imageUrl: String,
    val description: String,
    val movieLength: Int,
    val imageUrlBackdrop: String,
    val genres: List<String>,
    val countries: List<String>,
    val worldPremiere: String,
//    val trailers: List<Trailer>,
//    val persons: List<Person>,
    val errorMessage: String
)

data class AllMovies(
    val data: List<Movie>?,
    var error: String = ""
)

data class Trailer(
//    val _id: String,
    val name: String,
//    val site: String,
//    val size: Int,
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
