package com.kinofilms.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "movie_DB")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "alternativeName")
    val alternativeName: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "ratingKp")
    val ratingKp: Double,
    @ColumnInfo(name = "ratingImdb")
    val ratingImdb: Double,
//    val ratingTmdb: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "imageBackdropUrl")
    val imageBackdropUrl: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "movieLength")
    val movieLength: Int,
    @TypeConverters
    @ColumnInfo(name = "genres")
    val genres: List<String>,
    @ColumnInfo(name = "countries")
    val countries: List<String>,
    @ColumnInfo(name = "worldPremiere")
    val worldPremiere: String,
    @ColumnInfo(name = "premiereInRussia")
    val premiereInRussia: String,
//    val trailers: List<Trailer>,
    @ColumnInfo(name = "actors")
    val actors: List<Person>,
    @ColumnInfo(name = "filmCrew")
    val filmCrew: List<Person>,
//    val sequelsAndPrequels: List<SequelsAndPrequel>,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
    @ColumnInfo(name = "errorMessage")
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