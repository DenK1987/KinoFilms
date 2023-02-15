package com.kinofilms.ui.favoritemovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinofilms.databinding.ItemMovieBinding
import com.kinofilms.models.Movie

class FavoriteAdapter(
    private val onClickMovie: (movie: Movie) -> Unit
) :
    ListAdapter<Movie, FavoriteViewHolder>(FavoriteUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickMovie(getItem(position))
        }
    }
}