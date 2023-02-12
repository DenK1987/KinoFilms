package com.kinofilms.ui.movie.actorsadapter

import androidx.recyclerview.widget.DiffUtil
import com.kinofilms.models.Person

class ActorsUtilCallback : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}