package com.kinofilms.utils

import com.kinofilms.models.Movie
import com.kinofilms.network.models.moviesbytype.MovieResponse
import com.kinofilms.network.models.moviesbytype.MoviesByType
import retrofit2.HttpException
import retrofit2.Response

//fun Model.toMovie(): Movie {
//    return Movie(
//        id = this.id ?: 0,
//        name = this.name ?: "",
//        year = this.year ?: 0,
//        rating = this.rating?.kp ?: 0.0,
//        imageUrl = this.poster?.url ?: "",
//        genres = this.genres ?: emptyList()
//    )
//}
//
//fun List<Model>.toListMovie(): List<Movie> =
//    this.map { model -> model.toMovie() }


fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = this.id,
        name = this.name ?: "",
        year = this.year.toString(),
//        rating = this.rating?.kp.toString(),
//        rating = transformDouble(this.rating?.kp ?: 0.0).toString(),
        rating = String.format("%.1f", this.rating?.kp ?: 0.0).toDouble().toString(),
        imageUrl = this.poster?.url ?: "",
        errorMessage = ""
    )
}

fun List<MovieResponse>.toListMovie(): List<Movie> =
    this.map { model -> model.toMovie() }
        .filter {
            it.imageUrl.isNotEmpty() && it.name.isNotEmpty() && it.rating.toDouble() != 0.0
        }

