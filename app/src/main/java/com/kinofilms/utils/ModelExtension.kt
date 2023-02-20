package com.kinofilms.utils

import com.kinofilms.models.*
import com.kinofilms.network.models.*

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

fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = this.id,
        name = this.name ?: "",
        alternativeName = this.alternativeName ?: "",
        year = this.year.toString(),
        ratingKp = this.rating?.kp ?: 0.0,
        ratingImdb = this.rating?.imdb ?: 0.0,
        imageUrl = this.poster?.url ?: "",
        imageBackdropUrl = this.backdrop?.url ?: "",
        description = this.description ?: "",
        movieLength = this.movieLength ?: 0,
        genres = this.genres?.map { it.name ?: "" } ?: emptyList(),
        countries = this.countries?.map { it.name ?: "" } ?: emptyList(),
        worldPremiere = this.premiere?.world ?: "",
        premiereInRussia = this.premiere?.russia ?: "",
        actors = persons?.toListActors() ?: emptyList(),
        filmCrew = persons?.toListFilmCrew() ?: emptyList(),
        type = this.type ?: "",
        errorMessage = ""
    )
}

fun List<MovieResponse>.toListMovies(): List<Movie> =
    this.map { model -> model.toMovie() }

fun PersonModelResponse.toPersonModel(): PersonModel {
    return PersonModel(
        id = this.id,
        name = this.name ?: "",
        enName = this.enName ?: "",
        age = this.age ?: 0,
        growth = this.growth ?: 0,
        countAwards = this.countAwards ?: 0,
        photo = this.photo ?: "",
        sex = this.sex ?: "",
        birthday = this.birthday ?: "",
        birthPlace = this.birthPlace?.map { it.value ?: "" } ?: emptyList(),
        death = this.death ?: "",
        deathPlace = this.deathPlace?.map { it.value ?: "" } ?: emptyList(),
        profession = this.profession?.map { it.value ?: "" } ?: emptyList(),
        facts = this.facts?.toListFactsPerson() ?: emptyList()
    )
}

fun FactPersonResponse.toFactPerson(): FactPerson {
    return FactPerson(value = this.value ?: "")
}

fun List<FactPersonResponse>.toListFactsPerson(): List<FactPerson> =
    this.map { model -> model.toFactPerson() }