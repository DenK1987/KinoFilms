package com.kinofilms.ui.recommendations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.models.MovieCatalog
import com.kinofilms.repositories.KinoFilmsRepository
import com.kinofilms.utils.toListMoviesCatalog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    private val repository: KinoFilmsRepository
) : ViewModel() {

    private val _listPopularForeignMovies = MutableLiveData<List<MovieCatalog>>()
    val listPopularForeignMovies: LiveData<List<MovieCatalog>> = _listPopularForeignMovies

    private val _listPopularRussianMovies = MutableLiveData<List<MovieCatalog>>()
    val listPopularRussianMovies: LiveData<List<MovieCatalog>> = _listPopularRussianMovies

    private val _listPopularForeignSerials = MutableLiveData<List<MovieCatalog>>()
    val listPopularForeignSerials: LiveData<List<MovieCatalog>> = _listPopularForeignSerials

    private val _listPopularRussianSerials = MutableLiveData<List<MovieCatalog>>()
    val listPopularRussianSerials: LiveData<List<MovieCatalog>> = _listPopularRussianSerials

    fun getListPopularForeignMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllPopularForeignMovies()
            if (response.isSuccessful) {
                _listPopularForeignMovies.postValue(
                    response.body()?.docs?.toListMoviesCatalog() ?: emptyList()
                )
            } else {
                response.errorBody()
            }
        }
    }

    fun getListPopularRussianMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllPopularRussianMovies()
            if (response.isSuccessful) {
                _listPopularRussianMovies.postValue(
                    response.body()?.docs?.toListMoviesCatalog() ?: emptyList()
                )
            } else {
                response.errorBody()
            }
        }
    }

    fun getListPopularForeignSerials() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllPopularForeignSerials()
            if (response.isSuccessful) {
                _listPopularForeignSerials.postValue(
                    response.body()?.docs?.toListMoviesCatalog() ?: emptyList()
                )
            } else {
                response.errorBody()
            }
        }
    }

    fun getListPopularRussianSerials() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllPopularRussianSerials()
            if (response.isSuccessful) {
                _listPopularRussianSerials.postValue(
                    response.body()?.docs?.toListMoviesCatalog() ?: emptyList()
                )
            } else {
                response.errorBody()
            }
        }
    }
}