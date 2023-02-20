package com.kinofilms.ui.listpersons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
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

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.layoutProgress.isVisible = state
        }

        viewModel.listFilmCrew.observe(viewLifecycleOwner) { list ->
            initListFilmCrew(list.filmCrew)
        }
        viewModel.getListFilmCrew(args.idMovie.toInt())
    }

    private fun initListFilmCrew(list: List<Person>) {
        binding.listFilmCrew.run {
            if (adapter == null) {
                adapter = PersonsByProfessionAdapter {
                    findNavController().navigate(
                        ListFilmCrewFragmentDirections.actionListFilmCrewFragmentToPersonFragment(
                            it.id.toString(),
                            it.name
                        )
                    )
                }
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? PersonsByProfessionAdapter)?.submitList(list)
        }
    }
}