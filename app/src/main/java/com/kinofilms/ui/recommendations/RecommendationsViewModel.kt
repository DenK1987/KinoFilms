package com.kinofilms.ui.recommendations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.network.models.AllMoviesCatalogResponse
import com.kinofilms.repositories.KinoFilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    private val repository: KinoFilmsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean> = _state

    val listPopulars: StateFlow<Map<String, AllMoviesCatalogResponse>> by lazy {
        repository.getAllPopulars()
            .onStart { _state.emit(true) }
            .onCompletion { _state.emit(false) }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyMap())
    }
}