package com.kinofilms.ui.facts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kinofilms.databinding.ItemFactBinding
import com.kinofilms.models.FactPerson

class FactsViewHolder(
    private val binding: ItemFactBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(fact: FactPerson) {
        binding.apply {
            factValue.text = fact.value
        }
    }
}