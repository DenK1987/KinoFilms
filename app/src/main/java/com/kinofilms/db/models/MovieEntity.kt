package com.kinofilms.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kinofilms.models.Person

@Entity(tableName = "movie_DB")
data class MovieEntity(
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
    @ColumnInfo(name = "actors")
    val actors: List<Person>,
    @ColumnInfo(name = "filmCrew")
    val filmCrew: List<Person>,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)