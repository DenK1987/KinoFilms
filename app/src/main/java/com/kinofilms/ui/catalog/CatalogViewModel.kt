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

//    private var _catalog = MutableLiveData<AllMovies>()
//    var catalog: LiveData<AllMovies> = _catalog

    private val _id: MutableStateFlow<Int> = MutableStateFlow(0)
    val id: StateFlow<Int> = _id


    val catalog: StateFlow<AllMovies> by lazy {
        id.flatMapLatest { idFragment ->
            when (idFragment) {
                0 -> repository.getAllMovies()
                else -> emptyFlow()
            }


        }.stateIn(viewModelScope, SharingStarted.Lazily, AllMovies(emptyList()))
    }


//    fun getCatalogMovies(id: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//
//                when (id) {
//                    1 -> {
//                        val response = repository.getAllMovies()
//                        if (response.isSuccessful) {
//                            Log.e("CatalogViewModel", response.body()?.docs?.size.toString())
//                            _catalog.postValue(
//                                response.body()?.docs?.toListMovie() ?: emptyList()
//                            )
//                        } else {
//                            Log.e("CatalogViewModel", response.errorBody().toString())
////                    response.errorBody()
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("CatalogViewModel", e.message.toString())
//            }
//
//
//        }
//    }

//    fun getCatalogMovies(id: Int) {
//        viewModelScope.launch {
//            when (id) {
//                0 -> {
//
////                catalog = repository.getAllMovies().asLiveData()
//                    repository.getAllMovies().collectLatest { model ->
//                        _catalog.emit(model)
//                    }
//
//
//                }
//            }
//        }


    fun setId(id: Int) {
        _id.tryEmit(id)
    }


}