package com.kinofilms.ui.catalog.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kinofilms.models.Movie

class MoviesUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}