package com.kinofilms.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kinofilms.BuildConfig
import com.kinofilms.R
import com.kinofilms.databinding.FragmentProfileBinding
import com.kinofilms.ui.movie.MovieFragmentArgs
import com.kinofilms.ui.movie.MovieFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarTitle.text = getString(R.string.profile)
        }

        binding.buttonFavoritesMovies.setOnClickListener {
            findNavController().navigate(R.id.action_profile_fragment_to_favorite_movies_fragment)
        }

        binding.versionApp.text = "${getString(R.string.version_app)} ${BuildConfig.VERSION_NAME}"
    }
}