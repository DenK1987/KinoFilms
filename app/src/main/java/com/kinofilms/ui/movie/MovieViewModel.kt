package com.kinofilms.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.models.Movie
import com.kinofilms.repositories.KinoFilmsRepository
import com.kinofilms.repositories.SharedPreferencesRepository
import com.kinofilms.utils.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: KinoFilmsRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    private val isFavoriteMovie = MutableLiveData(false)

    fun getInfoMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(true)
            val response = repository.getMovie(id)
            if (response.isSuccessful) {
                _movie.postValue((response.body()?.toMovie()))
                _state.postValue(false)
            } else {
                response.errorBody()
            }
        }
    }

    private fun addFavoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movie.isFavorite = true
            repository.addMovieToFavorite(movie)
        }
    }

    private fun deleteFavoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movie.isFavorite = false
            repository.deleteMovieFromFavorite(movie)
        }
    }

    fun setFavoriteMovie(key: String, value: Boolean) =
        sharedPreferencesRepository.setFavoriteMovie(key, value)

    fun getFavoriteMovie(key: String) = sharedPreferencesRepository.getFavoriteMovie(key)

    fun selectFavoriteMovie(movie: Movie) {
        if (isFavoriteMovie.value == true) {
            deleteFavoriteMovie(movie)
        } else {
            addFavoriteMovie(movie)
        }
        isFavoriteMovie.value = !(isFavoriteMovie.value ?: true)
    }
}