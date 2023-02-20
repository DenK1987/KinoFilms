package com.kinofilms.ui.person

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
class PersonViewModel @Inject constructor(
    private val repository: KinoFilmsRepository
) : ViewModel() {

    private val _person = MutableLiveData<PersonModel>()
    val person: LiveData<PersonModel> = _person

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    fun getInfoPerson(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(true)
            val response = repository.getPerson(id)
            if (response.isSuccessful) {
                _person.postValue((response.body()?.toPersonModel()))
                _state.postValue(false)
            } else {
                response.errorBody()
            }
        }
    }
}