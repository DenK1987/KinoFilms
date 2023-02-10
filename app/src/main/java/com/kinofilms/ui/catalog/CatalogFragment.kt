package com.kinofilms.ui.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kinofilms.databinding.FragmentCatalogBinding
import com.kinofilms.models.Movie
import com.kinofilms.ui.catalog.adapter.MoviesAdapter
import com.kinofilms.ui.catalog.viewpager.PageFragmentDirections
import com.kinofilms.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private lateinit var binding: FragmentCatalogBinding

    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(ID_FRAGMENT)?.let {
            viewModel.setId(it)
        }

        lifecycleScope.launch {
            viewModel.catalog.collectLatest { result ->
                Log.e("KEK", result.toString())
                if (result.error.isEmpty())
                    initCatalogMovies(result.data ?: emptyList())
                else toast(result.error)
            }
        }
    }

    private fun initCatalogMovies(list: List<Movie>) {
        binding.catalogMovies.run {
            if (adapter == null) {
                adapter = MoviesAdapter {
//                    navigationFragments(
//                        parentFragmentManager,
//                        HeroFragment.newInstance(it.id.toString())
//                    )
                    findNavController().navigate(
                        PageFragmentDirections.actionPageFragmentToMovieFragment(
                            it.id.toString(),
                            it.name,
                            it.imageUrl,
                            it.year,
                            it.ratingKp,
                            it.ratingImdb,
                            it.ratingTmdb,
                            it.movieLength.toString(),
                            it.description,
                            it.worldPremiere
//                            it.genres.toString(),
//                            it.countries.toString()
                        )
                    )
                }
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            (adapter as? MoviesAdapter)?.submitList(list)
        }
    }

    companion object {
        private const val ID_FRAGMENT = "id_fragment"

        fun newInstance(id: Int): CatalogFragment {
            return CatalogFragment().apply {
                arguments = bundleOf(
                    ID_FRAGMENT to id
                )
            }
        }
    }
}