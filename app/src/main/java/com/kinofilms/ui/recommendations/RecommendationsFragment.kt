package com.kinofilms.ui.recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinofilms.R
import com.kinofilms.databinding.FragmentRecommendationsBinding
import com.kinofilms.models.MovieCatalog
import com.kinofilms.ui.recommendations.adapter.MoviesByRecommendationAdapter
import dagger.hilt.android.AndroidEntryPoint

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

        viewModel.listPopularForeignMovies.observe(viewLifecycleOwner) {
            binding.apply {
                initListPopularForeignMovies(it)
            }
        }
        viewModel.getListPopularForeignMovies()

        viewModel.listPopularRussianMovies.observe(viewLifecycleOwner) {
            binding.apply {
                initListPopularRussianMovies(it)
            }
        }
        viewModel.getListPopularRussianMovies()

        viewModel.listPopularForeignSerials.observe(viewLifecycleOwner) {
            binding.apply {
                initListPopularForeignSerials(it)
            }
        }
        viewModel.getListPopularForeignSerials()

        viewModel.listPopularRussianSerials.observe(viewLifecycleOwner) {
            binding.apply {
                initListPopularRussianSerials(it)
            }
        }
        viewModel.getListPopularRussianSerials()

        viewModel.listPopularCartoons.observe(viewLifecycleOwner) {
            binding.apply {
                initListPopularCartoons(it)
            }
        }
        viewModel.getListPopularCartoons()
    }

    private fun initListPopularForeignMovies(list: List<MovieCatalog>) {
        binding.listPopularForeignMovies.run {
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

    private fun initListPopularRussianMovies(list: List<MovieCatalog>) {
        binding.listPopularRussianMovies.run {
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

    private fun initListPopularForeignSerials(list: List<MovieCatalog>) {
        binding.listPopularForeignSerials.run {
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

    private fun initListPopularRussianSerials(list: List<MovieCatalog>) {
        binding.listPopularRussianSerials.run {
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

    private fun initListPopularCartoons(list: List<MovieCatalog>) {
        binding.listPopularCartoons.run {
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