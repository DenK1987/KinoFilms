package com.kinofilms.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
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
import com.kinofilms.utils.string
import com.kinofilms.utils.transformDurationMovie
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

private const val MAX_LINES_DESCRIPTION = 5

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    private val viewModel: MovieViewModel by viewModels()

    private val args: MovieFragmentArgs by navArgs()

    private var isFavoriteMovie = false

    private var isOpenDescription = false

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
            toolbarTitle.visibility = View.GONE
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarButton.visibility = View.VISIBLE
            toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            toolbarButton.setOnClickListener {
                if (!currentMovie.isFavorite) {
                    toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                    binding.buttonLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                    viewModel.setFavoriteMovie(currentMovie.id.toString(), true)
                    viewModel.selectFavoriteMovie(currentMovie)
                    val anim =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.anim_button_like)
                    toolbarButton.startAnimation(anim)
                } else {
                    toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    binding.buttonLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    viewModel.setFavoriteMovie(currentMovie.id.toString(), false)
                    viewModel.selectFavoriteMovie(currentMovie)
                }
            }
        }

        binding.apply {
            nestedScroll.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                toolbarCustom.toolbarCustom.isVisible = scrollY - oldScrollY > 0
            }

            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }

            buttonLike.setOnClickListener {
                if (!currentMovie.isFavorite) {
                    buttonLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                    toolbarCustom.toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                    viewModel.setFavoriteMovie(currentMovie.id.toString(), true)
                    viewModel.selectFavoriteMovie(currentMovie)
                    val anim =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.anim_button_like)
                    buttonLike.startAnimation(anim)
                } else {
                    buttonLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    toolbarCustom.toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    viewModel.setFavoriteMovie(currentMovie.id.toString(), false)
                    viewModel.selectFavoriteMovie(currentMovie)
                }
            }

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

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.layoutProgress.isVisible = state
            binding.toolbarCustom.toolbarCustom.isVisible = state
        }

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            currentMovie = movie
            binding.apply {
                nameMovie.text = movie.name
                if (movie.alternativeName.isNotEmpty()) {
                    alternativeNameMovie.text = movie.alternativeName
                } else alternativeNameMovie.visibility = View.GONE
                imageMovie.loadUrl(movie.imageBackdropUrl)
                imageMovie.alpha = 0.7F
                releaseYear.text = movie.year
                ratingKp.text = String.format("%.1f", movie.ratingKp).replace(",", ".")
                ratingImdb.text = String.format("%.1f", movie.ratingImdb).replace(",", ".")
                description.text = movie.description
                description.setOnClickListener {
                    if (!isOpenDescription) {
                        description.maxLines = Int.MAX_VALUE
                        isOpenDescription = true
                    } else {
                        description.maxLines = MAX_LINES_DESCRIPTION
                        isOpenDescription = false
                    }
                }
                if (movie.movieLength != 0) {
                    duration.text = transformDurationMovie(movie.movieLength)
                } else duration.visibility = View.GONE
                country.text = movie.countries.string()
                genreMovie.text = movie.genres.string()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                if (movie.worldPremiere.isNotEmpty()) {
                    datePremiereInWorld.text =
                        movie.worldPremiere.replaceAfter("T", "").trim('T')
                } else {
                    premiereInWorld.visibility = View.GONE
                    datePremiereInWorld.visibility = View.GONE
                }
                if (movie.premiereInRussia.isNotEmpty()) {
                    datePremiereInRussia.text =
                        movie.premiereInRussia.replaceAfter("T", "").trim('T')
                } else {
                    premiereInRussia.visibility = View.GONE
                    datePremiereInRussia.visibility = View.GONE
                }
                initListActors(movie.actors)
                buttonActors.text = movie.actors.size.toString()
                initListFilmCrew(movie.filmCrew)
                buttonFilmCrew.text = movie.filmCrew.size.toString()

                val flagFavorite = viewModel.getFavoriteMovie(currentMovie.id.toString())
                isFavoriteMovie = if (isFavoriteMovie != flagFavorite) {
                    toolbarCustom.toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                    buttonLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                    true
                } else {
                    toolbarCustom.toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    buttonLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    false
                }
            }
        }
        viewModel.getInfoMovie(args.idMovie.toInt())
    }

    private fun initListActors(list: List<Person>) {
        binding.listActors.run {
            if (adapter == null) {
                adapter = ActorsAdapter {
                    findNavController().navigate(
                        MovieFragmentDirections.actionMovieFragmentToPersonFragment(
                            it.id.toString(),
                            it.name
                        )
                    )
                }
                layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL)
            }
            (adapter as? ActorsAdapter)?.submitList(list)
        }
    }

    private fun initListFilmCrew(list: List<Person>) {
        binding.listFilmCrew.run {
            if (adapter == null) {
                adapter = FilmCrewAdapter {
                    findNavController().navigate(
                        MovieFragmentDirections.actionMovieFragmentToPersonFragment(
                            it.id.toString(),
                            it.name
                        )
                    )
                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            (adapter as? FilmCrewAdapter)?.submitList(list)
        }
    }
}