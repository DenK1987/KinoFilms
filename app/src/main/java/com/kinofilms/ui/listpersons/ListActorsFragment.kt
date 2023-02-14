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
import com.kinofilms.databinding.FragmentListActorsBinding
import com.kinofilms.models.Person
import com.kinofilms.ui.listpersons.adapter.PersonsByProfessionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActorsFragment : Fragment() {

    private lateinit var binding: FragmentListActorsBinding

    private val viewModel: ListPersonsViewModel by viewModels()

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

        viewModel.listActors.observe(viewLifecycleOwner) { list ->
            setListActors(list.actors.filter {
                it.enProfession == getString(R.string.profession_actor)
            })
        }
        viewModel.getListActors(args.idMovie.toInt())

        viewModel.listVoiceActors.observe(viewLifecycleOwner) { list ->
            if (list.actors.any {
                    it.enProfession == getString(R.string.profession_voice_actor)
                }) {
                setListVoiceActors(list.actors.filter {
                    it.enProfession == getString(R.string.profession_voice_actor)
                })
            } else binding.voiceActors.visibility = View.GONE
        }
        viewModel.getListVoiceActors(args.idMovie.toInt())


    }

    private fun setListActors(list: List<Person>) {
        binding.listActors.run {
            if (adapter == null) {
                adapter = PersonsByProfessionAdapter()
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? PersonsByProfessionAdapter)?.submitList(list)
        }
    }

    private fun setListVoiceActors(list: List<Person>) {
        binding.listVoiceActors.run {
            if (adapter == null) {
                adapter = PersonsByProfessionAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext())
            }
            (adapter as? PersonsByProfessionAdapter)?.submitList(list)
        }
    }
}