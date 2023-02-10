package com.kinofilms.ui.recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kinofilms.R
import com.kinofilms.databinding.FragmentRecommendationsBinding

class RecommendationsFragment : Fragment() {

    private lateinit var binding: FragmentRecommendationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecommendationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarTitle.text = getString(R.string.recommendations)
        }

    }
}