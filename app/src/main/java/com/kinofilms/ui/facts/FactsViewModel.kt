package com.kinofilms.ui.facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinofilms.models.PersonModel
import com.kinofilms.repositories.KinoFilmsRepository
import com.kinofilms.utils.toPersonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactsViewModel @Inject constructor(
    private val repository: KinoFilmsRepository
) : ViewModel() {

    private val _listFacts = MutableLiveData<PersonModel>()
    val listFacts: LiveData<PersonModel> = _listFacts

    fun getListFacts(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPerson(id)
            if (response.isSuccessful) {
                _listFacts.postValue((response.body()?.toPersonModel()))
            } else {
                response.errorBody()
            }
        }
    }
}