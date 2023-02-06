package com.kinofilms.ui.catalog.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinofilms.databinding.ItemMovieBinding
import com.kinofilms.models.Movie

class MoviesViewHolder(
    private val binding: ItemMovieBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.apply {
            textNameMovie.text = movie.name
            Glide.with(imageMovie)
                .load(movie.imageUrl)
                .into(imageMovie)
            textReleaseYear.text = movie.year.toString()
            textRating.text = movie.rating.toString()
        }
    }
}
