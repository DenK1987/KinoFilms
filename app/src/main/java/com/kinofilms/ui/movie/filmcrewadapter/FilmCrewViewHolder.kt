package com.kinofilms.ui.movie.filmcrewadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinofilms.databinding.ItemPersonBinding
import com.kinofilms.models.Person

class FilmCrewViewHolder(
    private val binding: ItemPersonBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(person: Person) {
        binding.apply {
            namePerson.text = person.name
            Glide.with(imagePerson)
                .load(person.photo)
                .into(imagePerson)
        }
    }
}
