package com.kinofilms.ui.catalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinofilms.databinding.ItemMovieBinding
import com.kinofilms.models.MovieCatalog

class MoviesAdapter(
    private val onClickMovie: (movie: MovieCatalog) -> Unit
) :
    ListAdapter<MovieCatalog, MoviesViewHolder>(MoviesUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickMovie(getItem(position))
        }
    }
}