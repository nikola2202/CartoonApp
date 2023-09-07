package com.example.cartoonapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _characterIdLiveData = MutableLiveData<com.example.cartoonapp.domain.models.Character?>()
    val characterIdLiveData: LiveData<com.example.cartoonapp.domain.models.Character?> = _characterIdLiveData

    fun fetchCharacter(characterId: Int) = viewModelScope.launch {
        val character = repository.getCharacterById(characterId)
        _characterIdLiveData.postValue(character)
    }
}