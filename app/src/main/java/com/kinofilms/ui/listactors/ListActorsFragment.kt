package com.kinofilms.ui.listactors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinofilms.R
import com.kinofilms.databinding.FragmentListActorsBinding
import com.kinofilms.models.Person
import com.kinofilms.ui.movie.MovieViewModel
import com.kinofilms.ui.movie.actorsadapter.ActorsAdapter
import com.kinofilms.ui.movie.filmcrewadapter.FilmCrewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActorsFragment : Fragment() {

    private lateinit var binding: FragmentListActorsBinding

    private val viewModel: MovieViewModel by viewModels()

    private val args: ListActorsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListActorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarTitle.text = getString(R.string.actors)
        }

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.apply {
                setListActors(movie.actors)
                setListFilmCrew(movie.filmCrew)
            }
        }
        viewModel.getInfoMovie(args.idMovie.toInt())




    }

    private fun setListActors(list: List<Person>) {
        binding.listActors.run {
            if (adapter == null) {
                adapter = ActorsAdapter()
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? ActorsAdapter)?.submitList(list)
        }
    }

    private fun setListFilmCrew(list: List<Person>) {
        binding.listVoiceActors.run {
            if (adapter == null) {
                adapter = FilmCrewAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext())
            }
            (adapter as? FilmCrewAdapter)?.submitList(list)
        }
    }
}