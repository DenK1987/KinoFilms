package com.kinofilms.utils

import com.kinofilms.models.Movie
import com.kinofilms.models.MovieCatalog
import com.kinofilms.models.Person
import com.kinofilms.network.models.MovieCatalogResponse
import com.kinofilms.network.models.MovieResponse
import com.kinofilms.network.models.PersonResponse

private const val PROF_ACTOR = "actor"
private const val PROF_VOICE_ACTOR = "voice_actor"

fun MovieCatalogResponse.toMovieCatalog(): MovieCatalog {
    return MovieCatalog(
        id = this.id,
        name = this.name ?: "",
        year = this.year.toString(),
        ratingKp = String.format("%.1f", this.rating?.kp ?: 0.0).toDouble().toString(),
        imageUrl = this.poster?.url ?: "",
        errorMessage = ""
    )
}

fun List<MovieCatalogResponse>.toListMoviesCatalog(): List<MovieCatalog> =
    this.map { model -> model.toMovieCatalog() }
        .filter {
            it.imageUrl.isNotEmpty() && it.name.isNotEmpty() && it.ratingKp.toDouble() != 0.0
        }

fun PersonResponse.toPerson(): Person {
    return Person(
        description = this.description ?: "",
        enName = this.enName ?: "",
        enProfession = this.enProfession ?: "",
        id = this.id ?: 0,
        name = this.name ?: "",
        photo = this.photo ?: ""
    )
}

fun List<PersonResponse>.toListActors(): List<Person> =
    this.map { model -> model.toPerson() }
        .filter {
            it.name.isNotEmpty()
                    && it.enProfession == PROF_ACTOR
                    || it.enProfession == PROF_VOICE_ACTOR
        }

fun List<PersonResponse>.toListFilmCrew(): List<Person> =
    this.map { model -> model.toPerson() }
        .filter {
            it.name.isNotEmpty()
                    && it.enProfession != PROF_ACTOR
                    && it.enProfession != PROF_VOICE_ACTOR
        }

fun MovieResponse.toMovie(): Movie {
    val listGenres = mutableListOf<String>()

    if (genres?.isNotEmpty() == true) {
        listGenres.add(genres.toString())
    }

    val listCountries = mutableListOf<String>()

    if (countries?.isNotEmpty() == true) {
        listCountries.add(countries.toString())
    }

    return Movie(
        id = this.id,
        name = this.name ?: "",
        year = this.year.toString(),
        ratingKp = String.format("%.1f", this.rating?.kp ?: 0.0).toDouble().toString(),
        ratingImdb = String.format("%.1f", this.rating?.imdb ?: 0.0).toDouble().toString(),
//        ratingTmdb = String.format("%.1f", this.rating?.tmdb ?: 0.0).toDouble().toString(),
        imageBackdropUrl = this.backdrop?.url ?: "",
        description = this.description ?: "",
        movieLength = this.movieLength ?: 0,
        genres = listGenres,
        countries = listCountries,
        worldPremiere = this.premiere?.world ?: "",
        premiereInRussia = this.premiere?.russia ?: "",
        actors = persons?.toListActors() ?: emptyList(),
        filmCrew = persons?.toListFilmCrew() ?: emptyList(),
        errorMessage = ""
    )
}

fun List<MovieResponse>.toListMovies(): List<Movie> =
    this.map { model -> model.toMovie() }