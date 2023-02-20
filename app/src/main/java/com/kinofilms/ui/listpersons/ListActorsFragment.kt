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
import androidx.recyclerview.widget.RecyclerView
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

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.layoutProgress.isVisible = state
        }

        viewModel.listActors.observe(viewLifecycleOwner) { list ->
            initList(list.actors.filter {
                it.enProfession == getString(R.string.profession_actor)
            }, binding.listActors)
        }
        viewModel.getListActors(args.idMovie.toInt())

        viewModel.listVoiceActors.observe(viewLifecycleOwner) { list ->
            if (list.actors.any {
                    it.enProfession == getString(R.string.profession_voice_actor)
                }) {
                initList(list.actors.filter {
                    it.enProfession == getString(R.string.profession_voice_actor)
                }, binding.listVoiceActors)
            } else binding.voiceActors.visibility = View.GONE
        }
        viewModel.getListVoiceActors(args.idMovie.toInt())
    }

    private fun initList(list: List<Person>, view: RecyclerView) {
        view.run {
            if (adapter == null) {
                adapter = PersonsByProfessionAdapter {
                    findNavController().navigate(
                        ListActorsFragmentDirections.actionListActorsFragmentToPersonFragment(
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