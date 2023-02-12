package com.kinofilms.ui.catalog.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kinofilms.models.MovieCatalog

class MoviesUtilCallback : DiffUtil.ItemCallback<MovieCatalog>() {

    override fun areItemsTheSame(oldItem: MovieCatalog, newItem: MovieCatalog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieCatalog, newItem: MovieCatalog): Boolean {
        return oldItem == newItem
    }
}