package com.kinofilms.ui.favoritemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kinofilms.R
import com.kinofilms.databinding.FragmentFavoriteMoviesBinding
import com.kinofilms.models.Movie
import com.kinofilms.ui.catalog.adapter.MoviesAdapter
import com.kinofilms.ui.favoritemovies.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMoviesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMoviesBinding

    private val viewModel: FavoriteMoviesViewModel by viewModels()

    private val args: FavoriteMoviesFragmentArgs by navArgs()

    private lateinit var recyclerFavoriteMovies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarTitle.text = getString(R.string.favorites_movies)
        }

        viewModel.listMovies.observe(viewLifecycleOwner) {
            initListFavoriteMovies(it)
        }
        viewModel.getListFavoriteMovies()
    }

    private fun initListFavoriteMovies(list: List<Movie>) {
        recyclerFavoriteMovies = binding.listFavoriteMovies
        recyclerFavoriteMovies.run {
            if (adapter == null) {
                adapter = FavoriteAdapter {
                    findNavController().navigate(
                        FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieFragment(
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