package com.kinofilms.ui.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.models.Movie
import com.kinofilms.repositories.KinoFilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: KinoFilmsRepository
) : ViewModel() {

    private val _listFavorite = MutableLiveData<List<Movie>>()
    val listFavorite: LiveData<List<Movie>> = _listFavorite

    fun getListFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            _listFavorite.postValue(repository.getFavoriteMovies())
        }
    }
}