package com.kinofilms.ui.listactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.models.Movie
import com.kinofilms.repositories.KinopoiskRepository
import com.kinofilms.utils.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListActorsViewModell @Inject constructor(
    private val repository: KinopoiskRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    fun getInfoMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMovie(id)
            if (response.isSuccessful) {
                _movie.postValue((response.body()?.toMovie()))
            } else {
                response.errorBody()
            }
        }
    }
}