package com.kinofilms.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kinofilms.R
import com.kinofilms.databinding.FragmentMovieBinding
import com.kinofilms.models.Movie
import com.kinofilms.models.Person
import com.kinofilms.ui.movie.actorsadapter.ActorsAdapter
import com.kinofilms.ui.movie.filmcrewadapter.FilmCrewAdapter
import com.kinofilms.utils.loadUrl
import com.kinofilms.utils.transformDurationMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    private val viewModel: MovieViewModel by viewModels()

    private val args: MovieFragmentArgs by navArgs()

    private var isFavoriteMovie = false

    private lateinit var currentMovie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarCustom.visibility = View.VISIBLE
            toolbarCustom.setBackgroundResource(R.color.color_transparent)
            toolbarTitle.visibility = View.GONE
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
//            toolbarEye.visibility = View.VISIBLE
//            toolbarEye.setImageResource(R.drawable.ic_outline_remove_red_eye_24)
            toolbarButton.visibility = View.VISIBLE
            toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            toolbarButton.setOnClickListener {
                if (!currentMovie.isFavorite) {
                    toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                    viewModel.setFavoriteMovie(currentMovie.id.toString(), true)
                    viewModel.selectFavoriteMovie(currentMovie)
                    val anim =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.anim_button_like)
                    toolbarButton.startAnimation(anim)
                } else {
                    toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    viewModel.setFavoriteMovie(currentMovie.id.toString(), false)
                    viewModel.selectFavoriteMovie(currentMovie)
                }
            }
        }

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            currentMovie = movie
            binding.apply {
                nameMovie.text = movie.name
                if (movie.alternativeName.isNotEmpty()) {
                    alternativeNameMovie.text = movie.alternativeName
                } else alternativeNameMovie.visibility = View.GONE
                alternativeNameMovie.text = movie.alternativeName
                imageMovie.loadUrl(movie.imageBackdropUrl)
                imageMovie.alpha = 0.7F
                releaseYear.text = movie.year
                ratingKp.text = String.format("%.1f", movie.ratingKp).replace(",", ".")
                ratingImdb.text = String.format("%.1f", movie.ratingImdb).replace(",", ".")
//                ratingTmdb.text = movie.ratingTmdb
                description.text = movie.description
                description.setOnClickListener {
                    description.maxLines = Int.MAX_VALUE
                }
                if (movie.movieLength != 0) {
                    duration.text = transformDurationMovie(movie.movieLength)
                } else duration.visibility = View.GONE
                country.text = movie.countries.toString()
                    .replace("Country(name=", "")
                    .filter { it != '[' && it != ']' && it != ')' }
                genreMovie.text = movie.genres.toString()
                    .replace("Genre(name=", "")
                    .filter { it != '[' && it != ']' && it != ')' }
                    .capitalize()
                if (movie.worldPremiere.isNotEmpty()) {
                    datePremiereInWorld.text =
                        movie.worldPremiere.replace("T00:00:00.000Z", "")
                } else {
                    premiereInWorld.visibility = View.GONE
                    datePremiereInWorld.visibility = View.GONE
                }
                if (movie.premiereInRussia.isNotEmpty()) {
                    datePremiereInRussia.text =
                        movie.premiereInRussia.replace("T00:00:00.000Z", "")
                } else {
                    premiereinRussia.visibility = View.GONE
                    datePremiereInRussia.visibility = View.GONE
                }
                initListActors(movie.actors)
                buttonActors.text = movie.actors.size.toString()
                initListFilmCrew(movie.filmCrew)
                buttonFilmCrew.text = movie.filmCrew.size.toString()

                val flagFavorite = viewModel.getFavoriteMovie(currentMovie.id.toString())
                isFavoriteMovie = if (isFavoriteMovie != flagFavorite) {
                    toolbarCustom.toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                    true
                } else {
                    toolbarCustom.toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    false
                }
            }
        }
//        Log.e("KEK", "MovieFragment ${args.idMovie}")

        viewModel.getInfoMovie(args.idMovie.toInt())

        binding.apply {
            actors.setOnClickListener {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToListActorsFragment(
                        args.idMovie
                    )
                )
            }
            buttonActors.setOnClickListener {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToListActorsFragment(
                        args.idMovie
                    )
                )
            }

            filmCrew.setOnClickListener {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToListFilmCrewFragment(
                        args.idMovie
                    )
                )
            }
            buttonFilmCrew.setOnClickListener {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToListFilmCrewFragment(
                        args.idMovie
                    )
                )
            }
        }
    }

    private fun initListActors(list: List<Person>) {
        binding.listActors.run {
            if (adapter == null) {
                adapter = ActorsAdapter()
                layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL)
            }
            (adapter as? ActorsAdapter)?.submitList(list)
        }
    }

    private fun initListFilmCrew(list: List<Person>) {
        binding.listFilmCrew.run {
            if (adapter == null) {
                adapter = FilmCrewAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            (adapter as? FilmCrewAdapter)?.submitList(list)
        }
    }
}