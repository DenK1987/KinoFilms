package com.kinofilms.ui.facts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinofilms.databinding.ItemFactBinding
import com.kinofilms.models.FactPerson

class FactsAdapter(
//    private val onClickFact: (fact: FactPerson) -> Unit
) :
    ListAdapter<FactPerson, FactsViewHolder>(FactsUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        return FactsViewHolder(
            ItemFactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bind(getItem(position))
//        holder.itemView.setOnClickListener {
//            onClickFact(getItem(position))
//        }
    }
}