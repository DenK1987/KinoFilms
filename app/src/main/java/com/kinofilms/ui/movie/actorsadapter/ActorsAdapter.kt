package com.kinofilms.ui.movie.actorsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinofilms.databinding.ItemPersonBinding
import com.kinofilms.models.Person

class ActorsAdapter(
    private val onClickActor: (person: Person) -> Unit
) :
    ListAdapter<Person, ActorsViewHolder>(ActorsUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickActor(getItem(position))
        }
    }
}