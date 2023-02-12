package com.kinofilms.ui.movie.actorsadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinofilms.databinding.ItemPersonBinding
import com.kinofilms.models.Person

class ActorsViewHolder(
    private val binding: ItemPersonBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(actor: Person) {
        binding.apply {
            namePerson.text = actor.name
            Glide.with(imagePerson)
                .load(actor.photo)
                .into(imagePerson)
        }
    }
}
