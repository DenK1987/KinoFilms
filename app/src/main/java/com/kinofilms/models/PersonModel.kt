package com.kinofilms.models

data class PersonModel(
    val age: Int,
    val birthPlace: List<String>,
    val birthday: String,
    val countAwards: Int,
    val death: String,
    val deathPlace: List<String>,
    val facts: List<FactPerson>,
    val enName: String,
    val growth: Int,
    val id: Int,
    val name: String,
    val photo: String,
    val profession: List<String>,
    val sex: String
   )

data class FactPerson(val value: String)