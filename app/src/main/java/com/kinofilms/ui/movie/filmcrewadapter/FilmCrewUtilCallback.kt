package com.kinofilms.ui.movie.filmcrewadapter

import androidx.recyclerview.widget.DiffUtil
import com.kinofilms.models.Person

class FilmCrewUtilCallback : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}