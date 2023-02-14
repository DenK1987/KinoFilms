package com.kinofilms.ui.catalog.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kinofilms.R
import com.kinofilms.databinding.FragmentPageBinding
import com.kinofilms.ui.catalog.FragmentType.*

class PageFragment : Fragment()  {  //, PopupMenu.OnMenuItemClickListener

    private lateinit var binding: FragmentPageBinding

//    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarTitle.text = getString(R.string.catalog)
            toolbarButton.visibility = View.VISIBLE
            toolbarButton.setImageResource(R.drawable.ic_baseline_tune_24)
//            toolbarButton.setImageResource(R.drawable.ic_baseline_filter_list_24)
//            toolbarButton.setOnClickListener {
//                PopupMenu(requireContext(), it).apply {
//                    setOnMenuItemClickListener(this@PageFragment)
//                    menuInflater.inflate(R.menu.menu_popup_filter_movies, menu)
//                }.show()
//            }
        }

        val newAdapter = ViewPagerAdapter(this@PageFragment)
        newAdapter.setItems(listOf(0, 1, 2, 3, 4))
        binding.viewPager.adapter = newAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                MOVIES.id -> tab.text = MOVIES.nameType
                TV_SERIES.id -> tab.text = TV_SERIES.nameType
                CARTOONS.id -> tab.text = CARTOONS.nameType
                ANIME.id -> tab.text = ANIME.nameType
                ANIMATED_SERIES.id -> tab.text = ANIMATED_SERIES.nameType
            }
        }.attach()
    }
//
//    override fun onMenuItemClick(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.filter_by_popularity -> {
//                viewModel.sortedMoviesByPopularity()
//                true
//            }
//            R.id.filter_by_rating -> {
//                viewModel.sortedMoviesByRating()
//                true
//            }
//            R.id.filter_by_release_date -> {
//                viewModel.sortedMoviesByReleaseDate()
//                true
//            }
//            else -> false
//        }
//    }
}