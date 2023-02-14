package com.kinofilms.ui.recommendations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinofilms.databinding.ItemMovieRecommendationsBinding
import com.kinofilms.models.MovieCatalog

class MoviesByRecommendationAdapter(
    private val onClickMovie: (movie: MovieCatalog) -> Unit
) :
    ListAdapter<MovieCatalog, MoviesByRecommendationViewHolder>(MoviesByRecommendationUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByRecommendationViewHolder {
        return MoviesByRecommendationViewHolder(
            ItemMovieRecommendationsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesByRecommendationViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickMovie(getItem(position))
        }
    }
}