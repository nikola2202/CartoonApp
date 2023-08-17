package com.example.cartoonapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _characterIdLiveData = MutableLiveData<GetCharacterByIdResponse?>()
    val characterIdLiveData: LiveData<GetCharacterByIdResponse?> = _characterIdLiveData

    fun refreshCharacter(characterId:Int) {
        viewModelScope.launch {
            val response = repository.getCharatcterById(characterId)
            _characterIdLiveData.postValue(response)
        }
    }
}