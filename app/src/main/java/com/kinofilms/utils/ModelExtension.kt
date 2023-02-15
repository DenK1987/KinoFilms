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
        ratingKp = this.rating?.kp ?: 0.0,
        imageUrl = this.poster?.url ?: "",
        votesKp = this.votes?.kp ?: 0,
        votesImdb = this.votes?.imdb ?: 0,
        errorMessage = ""
    )
}

fun List<MovieCatalogResponse>.toListMoviesCatalog(): List<MovieCatalog> =
    this.map { model -> model.toMovieCatalog() }
        .filter {
            it.imageUrl.isNotEmpty() && it.name.isNotEmpty() && it.ratingKp != 0.0
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

//fun SequelsAndPrequelResponse.toSequelsAndPrequel(): SequelsAndPrequel {
//    return SequelsAndPrequel(
//        _id = this._id ?: "",
//        alternativeName = this.alternativeName ?: "",
//        id = this.id ?: 0,
//        name = this.name ?: "",
//        type = this.type ?: ""
//    )
//}
//
//fun List<SequelsAndPrequelResponse>.toListSequelsAndPrequel(): List<SequelsAndPrequel> =
//    this.map { model -> model.toSequelsAndPrequel() }

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
        alternativeName = this.alternativeName ?: "",
        year = this.year.toString(),
        ratingKp = this.rating?.kp ?: 0.0,
        ratingImdb = this.rating?.imdb ?: 0.0,
//        ratingTmdb = String.format("%.1f", this.rating?.tmdb ?: 0.0).toDouble().toString(),
        imageUrl = this.poster?.url ?: "",
        imageBackdropUrl = this.backdrop?.url ?: "",
        description = this.description ?: "",
        movieLength = this.movieLength ?: 0,
        genres = listGenres,
        countries = listCountries,
        worldPremiere = this.premiere?.world ?: "",
        premiereInRussia = this.premiere?.russia ?: "",
        actors = persons?.toListActors() ?: emptyList(),
        filmCrew = persons?.toListFilmCrew() ?: emptyList(),
//        sequelsAndPrequels = sequelsAndPrequels?.toListSequelsAndPrequel() ?: emptyList(),
        errorMessage = ""
    )
}

fun List<MovieResponse>.toListMovies(): List<Movie> =
    this.map { model -> model.toMovie() }

//fun MovieEntity.toMovieE(): Movie {
//    return Movie(
//        id = this.id,
//        name = this.name,
//        alternativeName = this.alternativeName,
//        year = this.year,
//        ratingKp = this.ratingKp,
//        ratingImdb = this.ratingImdb,
//        imageBackdropUrl = this.imageBackdropUrl,
//        description = this.description,
//        movieLength = this.movieLength,
//        genres = this.genres,
//        countries = this.countries,
//        worldPremiere = this.worldPremiere,
//        premiereInRussia = this.premiereInRussia,
//        actors = this.actors,
//        filmCrew = this.filmCrew,
//        errorMessage = "",
//        isFavorite = this.isFavorite
//    )
//}