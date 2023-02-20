package com.kinofilms.ui.facts.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kinofilms.models.FactPerson

class FactsUtilCallback: DiffUtil.ItemCallback<FactPerson>() {

    override fun areItemsTheSame(oldItem: FactPerson, newItem: FactPerson): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: FactPerson, newItem: FactPerson): Boolean {
        return oldItem == newItem
    }
}