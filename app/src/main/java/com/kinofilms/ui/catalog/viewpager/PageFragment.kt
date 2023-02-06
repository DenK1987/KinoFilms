package com.kinofilms.ui.catalog.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kinofilms.R
import com.kinofilms.databinding.FragmentTabBinding
import com.kinofilms.ui.catalog.FragmentType.*

class PageFragment : Fragment() {

    private lateinit var binding: FragmentTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarCustom.apply {
            toolbarTitle.text = getString(R.string.catalog)
            toolbarButton.visibility = View.VISIBLE
            toolbarButton.setImageResource(R.drawable.ic_baseline_tune_24)
        }


        val newAdapter = ViewPagerAdapter(this@PageFragment)
        newAdapter.setItems(listOf(0, 1, 2, 3, 4))
        binding.viewPager.adapter = newAdapter



        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->

            when (position) {
                MOVIES.id -> tab.text = MOVIES.nameType
                1 -> tab.text = "Сериалы"
                2 -> tab.text = "Мультфильмы"
                3 -> tab.text = "Фильмы"
                4 -> tab.text = "Фильмы"
            }




//            tab.text = "OBJECT ${(position + 1)}"
        }.attach()


    }
}