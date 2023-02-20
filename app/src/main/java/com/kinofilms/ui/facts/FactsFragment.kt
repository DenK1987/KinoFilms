package com.kinofilms.ui.facts

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
import com.kinofilms.databinding.FragmentFactsBinding
import com.kinofilms.models.FactPerson
import com.kinofilms.ui.facts.adapter.FactsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactsFragment : Fragment() {

    private lateinit var binding: FragmentFactsBinding

    private val viewModel: FactsViewModel by viewModels()

    private val args: FactsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarTitle.text = getString(R.string.facts)
        }

        viewModel.listFacts.observe(viewLifecycleOwner) { list ->
            initListFacts(list.facts)
        }
        viewModel.getListFacts(args.idPerson.toInt())
    }

    private fun initListFacts(list: List<FactPerson>) {
        binding.listFacts.run {
            if (adapter == null) {
                adapter = FactsAdapter()
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? FactsAdapter)?.submitList(list)
        }
    }
}