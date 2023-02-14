package com.kinofilms.ui.listpersons.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinofilms.databinding.ItemPersonProfessionBinding
import com.kinofilms.models.Person

class PersonsByProfessionViewHolder(
    private val binding: ItemPersonProfessionBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(person: Person) {
        binding.apply {
            nameActor.text = person.name
            enNameActor.text = person.enName
            enProfession.text = person.enProfession
            Glide.with(imageActor)
                .load(person.photo)
                .into(imageActor)
        }
    }
}
