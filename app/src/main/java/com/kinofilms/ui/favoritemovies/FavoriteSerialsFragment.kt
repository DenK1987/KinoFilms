package com.kinofilms.ui.favoritemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kinofilms.R
import com.kinofilms.databinding.FragmentFavoriteSerialsBinding
import com.kinofilms.models.Movie
import com.kinofilms.ui.favoritemovies.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteSerialsFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteSerialsBinding

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteSerialsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarTitle.text = getString(R.string.favorites_serials)
            toolbarButton.visibility = View.VISIBLE
            toolbarButton.setImageResource(R.drawable.ic_baseline_delete_outline_24)
        }

        viewModel.listFavorite.observe(viewLifecycleOwner) {
            initListFavorite(it.filter { movie ->
                movie.type == getString(R.string.type_tv_series)
            })
        }
        viewModel.getListFavorite()
    }

    private fun initListFavorite(list: List<Movie>) {
        binding.listFavoriteSerials.run {
            if (adapter == null) {
                adapter = FavoriteAdapter {
                    findNavController().navigate(
                        FavoriteSerialsFragmentDirections.actionFavoriteSerialsFragmentToMovieFragment(
                            it.id.toString()
                        )
                    )
                }
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            (adapter as? FavoriteAdapter)?.submitList(list)
        }
    }
}