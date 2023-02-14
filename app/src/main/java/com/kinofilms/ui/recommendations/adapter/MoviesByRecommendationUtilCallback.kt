package com.kinofilms.ui.recommendations.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kinofilms.models.Movie
import com.kinofilms.models.MovieCatalog

class MoviesByRecommendationUtilCallback : DiffUtil.ItemCallback<MovieCatalog>() {

    override fun areItemsTheSame(oldItem: MovieCatalog, newItem: MovieCatalog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieCatalog, newItem: MovieCatalog): Boolean {
        return oldItem == newItem
    }
}