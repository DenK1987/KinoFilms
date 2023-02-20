package com.kinofilms.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.models.AllMoviesCatalog
import com.kinofilms.repositories.KinoFilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: KinoFilmsRepository
) : ViewModel() {

    private val _id: MutableStateFlow<Int> = MutableStateFlow(0)
    val id: StateFlow<Int> = _id

    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean> = _state

    val catalog: StateFlow<AllMoviesCatalog> by lazy {
        id.flatMapLatest { idFragment ->
            when (idFragment) {
                0 -> repository.getAllMovies()
                    .onStart { _state.emit(true) }
                    .onCompletion { _state.emit(false) }
                1 -> repository.getAllSerials()
                    .onStart { _state.emit(true) }
                    .onCompletion { _state.emit(false) }
                2 -> repository.getAllCartoons()
                    .onStart { _state.emit(true) }
                    .onCompletion { _state.emit(false) }
                3 -> repository.getAllAnime()
                    .onStart { _state.emit(true) }
                    .onCompletion { _state.emit(false) }
                4 -> repository.getAllAnimatedSeries()
                    .onStart { _state.emit(true) }
                    .onCompletion { _state.emit(false) }
                else -> emptyFlow()
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, AllMoviesCatalog(emptyList()))
    }

    fun setId(id: Int) {
        _id.tryEmit(id)
    }
}