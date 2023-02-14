package com.kinofilms.ui.recommendations.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinofilms.databinding.ItemMovieRecommendationsBinding
import com.kinofilms.models.MovieCatalog

class MoviesByRecommendationViewHolder(
    private val binding: ItemMovieRecommendationsBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieCatalog) {
        binding.apply {
            textNameMovie.text = movie.name
            Glide.with(imageMovie)
                .load(movie.imageUrl)
                .into(imageMovie)
            textReleaseYear.text = movie.year
            textRating.text = String.format("%.1f", movie.ratingKp).replace(",", ".")
        }
    }
}