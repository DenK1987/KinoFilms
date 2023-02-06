package com.kinofilms.ui.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kinofilms.R
import com.kinofilms.databinding.FragmentCatalogBinding
import com.kinofilms.models.Movie
import com.kinofilms.ui.catalog.adapter.MoviesAdapter
import com.kinofilms.ui.catalog.viewpager.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ID_FRAGMENT = "id_fragment"

@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private lateinit var binding: FragmentCatalogBinding

    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.toolbarCustom.apply {
//            toolbarTitle.text = getString(R.string.catalog)
//            toolbarButton.visibility = View.VISIBLE
//            toolbarButton.setImageResource(R.drawable.ic_baseline_tune_24)
//        }
        arguments?.getInt(ID_FRAGMENT)?.let {
            binding.text.text = it.toString()
//            viewModel.getCatalogMovies(it)
            viewModel.setId(it)
        }

        lifecycleScope.launch {
            viewModel.catalog.collectLatest { result ->
                Log.e("KEK", result.toString())
                if (result.error.isEmpty())
                    initCatalogMovies(result.data ?: emptyList())
                else Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun initCatalogMovies(list: List<Movie>) {
        binding.catalogMovies.run {
            if (adapter == null) {
                adapter = MoviesAdapter {
//                    navigationFragments(
//                        parentFragmentManager,
//                        HeroFragment.newInstance(it.id.toString())
//                    )
                }
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            (adapter as? MoviesAdapter)?.submitList(list)
        }
    }

    companion object {
        fun newInstance(id: Int): CatalogFragment {
            return CatalogFragment().apply {
                arguments = bundleOf(
                    ID_FRAGMENT to id
                )
            }
        }
    }
}