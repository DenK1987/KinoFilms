package com.kinofilms.ui.listpersons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinofilms.databinding.ItemPersonProfessionBinding
import com.kinofilms.models.Person

class PersonsByProfessionAdapter(
    private val onClickPerson: (person: Person) -> Unit
) :
    ListAdapter<Person, PersonsByProfessionViewHolder>(PersonsByProfessionUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsByProfessionViewHolder {
        return PersonsByProfessionViewHolder(
            ItemPersonProfessionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PersonsByProfessionViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickPerson(getItem(position))
        }
    }
}