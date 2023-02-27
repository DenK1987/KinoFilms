package com.kinofilms.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinofilms.R
import com.kinofilms.databinding.FragmentPersonBinding
import com.kinofilms.models.FactPerson
import com.kinofilms.ui.person.factsadapter.FactsPersonAdapter
import com.kinofilms.utils.loadUrl
import com.kinofilms.utils.string
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding

    private val viewModel: PersonViewModel by viewModels()

    private val args: PersonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarTitle.text = args.namePerson
            toolbarButton.visibility = View.VISIBLE
            toolbarButton.setImageResource(R.drawable.ic_baseline_share_24)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.layoutProgress.isVisible = state
        }

        viewModel.person.observe(viewLifecycleOwner) { person ->
            binding.apply {
                namePerson.text = person.name
                enNamePerson.text = person.enName
                photoPerson.loadUrl(person.photo)
                profession.text = person.profession.string()
                if (person.facts.isNotEmpty()) {
                    initListFacts(person.facts)
                    buttonFacts.text = person.facts.size.toString()
                } else {
                    listFacts.isVisible = false
                    facts.isVisible = false
                    buttonFacts.isVisible = false
                }

            }
        }
        viewModel.getInfoPerson(args.idPerson.toInt())

        binding.apply {
            buttonMoreDetailed.setOnClickListener {
                findNavController().navigate(
                    PersonFragmentDirections.actionPersonFragmentToDetailedInfoPersonFragment(
                        args.idPerson,
                        args.namePerson
                    )
                )
            }

            facts.setOnClickListener {
                findNavController().navigate(
                    PersonFragmentDirections.actionPersonFragmentToFactsFragment(
                        args.idPerson
                    )
                )
            }
            buttonFacts.setOnClickListener {
                findNavController().navigate(
                    PersonFragmentDirections.actionPersonFragmentToFactsFragment(
                        args.idPerson
                    )
                )
            }
        }
    }

    private fun initListFacts(list: List<FactPerson>) {
        binding.listFacts.run {
            if (adapter == null) {
                adapter = FactsPersonAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            (adapter as? FactsPersonAdapter)?.submitList(list)
        }
    }
}