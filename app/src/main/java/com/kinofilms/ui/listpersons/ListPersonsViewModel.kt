package com.kinofilms.ui.listpersons

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
class ListPersonsViewModel @Inject constructor(
    private val repository: KinoFilmsRepository
) : ViewModel() {

    private val _listActors = MutableLiveData<Movie>()
    val listActors: LiveData<Movie> = _listActors

    private val _listVoiceActors = MutableLiveData<Movie>()
    val listVoiceActors: LiveData<Movie> = _listVoiceActors

    private val _listFilmCrew = MutableLiveData<Movie>()
    val listFilmCrew: LiveData<Movie> = _listFilmCrew

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    fun getListActors(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(true)
            val response = repository.getMovie(id)
            if (response.isSuccessful) {
                _listActors.postValue((response.body()?.toMovie()))
                _state.postValue(false)
            } else {
                response.errorBody()
            }
        }
    }

    fun getListVoiceActors(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(true)
            val response = repository.getMovie(id)
            if (response.isSuccessful) {
                _listVoiceActors.postValue((response.body()?.toMovie()))
                _state.postValue(false)
            } else {
                response.errorBody()
            }
        }
    }

    fun getListFilmCrew(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(true)
            val response = repository.getMovie(id)
            if (response.isSuccessful) {
                _listFilmCrew.postValue((response.body()?.toMovie()))
                _state.postValue(false)
            } else {
                response.errorBody()
            }
        }
    }
}