package com.kinofilms.ui.person.factsadapter

import androidx.recyclerview.widget.RecyclerView
import com.kinofilms.databinding.ItemFactPersonBinding
import com.kinofilms.models.FactPerson

class FactsPersonViewHolder(
    private val binding: ItemFactPersonBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(fact: FactPerson) {
        binding.apply {
            factValue.text = fact.value
        }
    }
}