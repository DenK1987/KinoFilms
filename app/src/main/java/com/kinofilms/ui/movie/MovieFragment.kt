package com.kinofilms.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kinofilms.R
import com.kinofilms.databinding.FragmentMovieBinding
import com.kinofilms.utils.loadUrl
import com.kinofilms.utils.transformDurationMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    private val viewModel: MovieViewModel by viewModels()

    private val args: MovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarCustom.visibility = View.VISIBLE
            toolbarCustom.setBackgroundResource(R.color.color_transparent)
            toolbarTitle.visibility = View.GONE
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarEye.visibility = View.VISIBLE
            toolbarEye.setImageResource(R.drawable.ic_outline_remove_red_eye_24)
            toolbarButton.visibility = View.VISIBLE
            toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.apply {
                nameMovie.text = movie.name
                imageMovie.loadUrl(movie.imageUrlBackdrop)
                releaseYear.text = movie.year
                ratingKp.text = movie.ratingKp
            }
        }
        Log.e("KEK", "MovieFragment ${args.idMovie}")

        viewModel.getInfoMovie(args.idMovie.toInt())

//        binding.apply {
//            nameMovie.text = args.nameMovie
//            imageMovie.loadUrl(args.imageMovie)
//            imageMovie.alpha = 0.7F
//            releaseYear.text = args.releaseYear
//            ratingKp.text = args.ratingKp
//            ratingImdb.text = args.ratingImdb
//            ratingTmdb.text = args.ratingTmdb
//            duration.text = args.lengthMovie?.let { transformDurationMovie(it.toInt()) }
////            premiere.text = args.worldPremiere
//            description.text = args.description
////            genreMovie.text = args.genres
////            country.text = args.countries
//        }


    }
}