package com.kinofilms.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.models.AllMovies
import com.kinofilms.repositories.KinopoiskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: KinopoiskRepository
) : ViewModel() {

    private val _id: MutableStateFlow<Int> = MutableStateFlow(0)
    val id: StateFlow<Int> = _id

    val catalog: StateFlow<AllMovies> by lazy {
        id.flatMapLatest { idFragment ->
            when (idFragment) {
                0 -> repository.getAllMovies()
                1 -> repository.getAllSerials()
                2 -> repository.getAllCartoons()
                3 -> repository.getAllAnime()
                4 -> repository.getAllAnimatedSeries()
                else -> emptyFlow()
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, AllMovies(emptyList()))
    }

    fun setId(id: Int) {
        _id.tryEmit(id)
    }
}