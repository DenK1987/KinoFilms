package com.kinofilms.ui.person.factsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinofilms.databinding.ItemFactPersonBinding
import com.kinofilms.models.FactPerson

class FactsPersonAdapter(
//    private val onClickFact: (fact: FactPerson) -> Unit
) :
    ListAdapter<FactPerson, FactsPersonViewHolder>(FactsPersonUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsPersonViewHolder {
        return FactsPersonViewHolder(
            ItemFactPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FactsPersonViewHolder, position: Int) {
        holder.bind(getItem(position))
//        holder.itemView.setOnClickListener {
//            onClickFact(getItem(position))
//        }
    }
}