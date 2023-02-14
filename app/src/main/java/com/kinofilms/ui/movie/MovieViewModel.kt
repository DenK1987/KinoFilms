package com.kinofilms.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.models.Movie
import com.kinofilms.repositories.KinoFilmsRepository
import com.kinofilms.utils.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: KinoFilmsRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    fun getInfoMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            Log.e("KEK", "MovieViewModel $id")
            val response = repository.getMovie(id)
            if (response.isSuccessful) {
                _movie.postValue((response.body()?.toMovie()))
            } else {
                response.errorBody()
            }
        }
    }

}