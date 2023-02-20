package com.kinofilms.ui.movie.filmcrewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinofilms.databinding.ItemPersonBinding
import com.kinofilms.models.Person

class FilmCrewAdapter(
    private val onClickFilmCrew: (person: Person) -> Unit
) :
    ListAdapter<Person, FilmCrewViewHolder>(FilmCrewUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmCrewViewHolder {
        return FilmCrewViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmCrewViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickFilmCrew(getItem(position))
        }
    }
}