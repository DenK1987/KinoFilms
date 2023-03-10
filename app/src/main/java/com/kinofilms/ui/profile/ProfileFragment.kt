package com.kinofilms.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kinofilms.BuildConfig
import com.kinofilms.R
import com.kinofilms.databinding.FragmentProfileBinding
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

        binding.apply {
            buttonFavoritesMovies.setOnClickListener {
                findNavController().navigate(R.id.action_profile_fragment_to_favorite_movies_fragment)
            }

            buttonFavoritesSerials.setOnClickListener {
                findNavController().navigate(R.id.action_profile_fragment_to_favorite_serials_fragment)
            }

            buttonFavoritesCartoons.setOnClickListener {
                findNavController().navigate(R.id.action_profile_fragment_to_favorite_cartoons_fragment)
            }

            versionApp.text = "${getString(R.string.version_app)} ${BuildConfig.VERSION_NAME}"
        }
    }
}