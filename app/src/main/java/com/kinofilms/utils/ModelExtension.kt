package com.kinofilms.utils

import com.kinofilms.models.Movie
import com.kinofilms.network.models.MovieResponse

fun MovieResponse.toMovie(): Movie {
    val listGenres = arrayListOf<String>()

    if (genres?.isNotEmpty() == true) {
        listGenres.add(genres.toString())
    }

    val listCountries = arrayListOf<String>()

    if (countries?.isNotEmpty() == true) {
        listCountries.add(countries.toString())
    }

    return Movie(
        id = this.id,
        name = this.name ?: "",
        year = this.year.toString(),
        ratingKp = String.format("%.1f", this.rating?.kp ?: 0.0).toDouble().toString(),
        ratingImdb = String.format("%.1f", this.rating?.imdb ?: 0.0).toDouble().toString(),
        ratingTmdb = String.format("%.1f", this.rating?.tmdb ?: 0.0).toDouble().toString(),
        imageUrl = this.poster?.url ?: "",
        description = this.description ?: "",
        movieLength = this.movieLength ?: 0,
        imageUrlBackdrop = this.backdrop?.url ?: "",
        genres = listGenres,
        countries = listCountries,
        worldPremiere = this.premiere?.world ?: "",
        errorMessage = ""
    )
}

fun List<MovieResponse>.toListMovies(): List<Movie> =
    this.map { model -> model.toMovie() }
        .filter {
            it.imageUrl.isNotEmpty() && it.name.isNotEmpty() && it.ratingKp.toDouble() != 0.0
        }