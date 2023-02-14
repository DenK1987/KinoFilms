package com.kinofilms.ui.listpersons

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
import com.kinofilms.databinding.FragmentListFilmCrewBinding
import com.kinofilms.models.Person
import com.kinofilms.ui.listpersons.adapter.PersonsByProfessionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFilmCrewFragment : Fragment() {

    private lateinit var binding: FragmentListFilmCrewBinding

    private val viewModel: ListPersonsViewModel by viewModels()

    private val args: ListActorsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListFilmCrewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarTitle.text = getString(R.string.film_crew)
        }

        viewModel.listFilmCrew.observe(viewLifecycleOwner) { list ->
            setListFilmCrew(list.filmCrew)
        }
        viewModel.getListFilmCrew(args.idMovie.toInt())
    }

    private fun setListFilmCrew(list: List<Person>) {
        binding.listFilmCrew.run {
            if (adapter == null) {
                adapter = PersonsByProfessionAdapter()
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? PersonsByProfessionAdapter)?.submitList(list)
        }
    }
}