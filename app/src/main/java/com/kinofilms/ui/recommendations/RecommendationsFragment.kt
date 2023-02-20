package com.kinofilms.ui.recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kinofilms.R
import com.kinofilms.databinding.FragmentRecommendationsBinding
import com.kinofilms.models.MovieCatalog
import com.kinofilms.ui.recommendations.adapter.MoviesByRecommendationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import com.kinofilms.ui.recommendations.RecommendationType.*
import com.kinofilms.utils.toListMoviesCatalog
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecommendationsFragment : Fragment() {

    private lateinit var binding: FragmentRecommendationsBinding

    private val viewModel: RecommendationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecommendationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarTitle.text = getString(R.string.recommendations)
        }

        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                binding.layoutProgress.isVisible = state
            }
        }

        lifecycleScope.launch {
            viewModel.listPopulars.collectLatest { data ->
                binding.apply {
                    data.forEach { (key, allMoviesCatalogResponse) ->
                        when (key) {
                            POPULAR_FOREIGN_MOVIES.name -> initList(
                                allMoviesCatalogResponse.docs?.toListMoviesCatalog() ?: emptyList(),
                                listPopularForeignMovies
                            )
                            POPULAR_FOREIGN_SERIALS.name -> initList(
                                allMoviesCatalogResponse.docs?.toListMoviesCatalog() ?: emptyList(),
                                listPopularForeignSerials
                            )
                            POPULAR_RUSSIAN_MOVIES.name -> initList(
                                allMoviesCatalogResponse.docs?.toListMoviesCatalog() ?: emptyList(),
                                listPopularRussianMovies
                            )
                            POPULAR_RUSSIAN_SERIALS.name -> initList(
                                allMoviesCatalogResponse.docs?.toListMoviesCatalog() ?: emptyList(),
                                listPopularRussianSerials
                            )
                            POPULAR_CARTOONS.name -> initList(
                                allMoviesCatalogResponse.docs?.toListMoviesCatalog() ?: emptyList(),
                                listPopularCartoons
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initList(list: List<MovieCatalog>, view: RecyclerView) {
        view.run {
            if (adapter == null) {
                adapter = MoviesByRecommendationAdapter {
                    findNavController().navigate(
                        RecommendationsFragmentDirections.actionRecommendationsFragmentToMovieFragment(
                            it.id.toString()
                        )
                    )
                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            (adapter as? MoviesByRecommendationAdapter)?.submitList(list)
        }
    }
}