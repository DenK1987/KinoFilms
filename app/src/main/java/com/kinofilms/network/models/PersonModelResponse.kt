package com.kinofilms.network.models

data class PersonModelResponse(
    val age: Int?,
    val birthPlace: List<BirthPlace>?,
    val birthday: String?,
    val countAwards: Int?,
    val death: String?,
    val deathPlace: List<DeathPlace>?,
    val enName: String?,
    val facts: List<FactPersonResponse>?,
    val growth: Int?,
    val id: Int,
    val movies: List<MoviePersonResponse>?,
    val name: String?,
    val photo: String?,
    val profession: List<Profession>?,
    val sex: String?,
    val spouses: List<Spouse>?
)

data class BirthPlace(val value: String?)

data class DeathPlace(val value: String?)

data class FactPersonResponse(val value: String?)

data class MoviePersonResponse(
    val alternativeName: String?,
    val description: String?,
    val general: Boolean?,
    val id: Int?,
    val name: String?,
    val rating: Double?
)

data class Profession(val value: String?)

data class Spouse(
    val _id: String?,
    val children: Int?,
    val divorced: Boolean?,
    val divorcedReason: String?,
    val id: Int?,
    val name: String?,
    val relation: String?
)