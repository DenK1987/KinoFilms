package com.kinofilms.ui.catalog

private const val TYPE_MOVIES = "Фильмы"
private const val TYPE_TV_SERIES = "Сериалы"
private const val TYPE_CARTOONS = "Мультфильмы"
private const val TYPE_ANIME = "Аниме"
private const val TYPE_ANIMATED_SERIES = "Мультсериалы"

enum class FragmentType(val id: Int, val nameType: String) {
    MOVIES(0, TYPE_MOVIES),
    TV_SERIES(1, TYPE_TV_SERIES),
    CARTOONS(2, TYPE_CARTOONS),
    ANIME(3, TYPE_ANIME),
    ANIMATED_SERIES(4, TYPE_ANIMATED_SERIES)
}