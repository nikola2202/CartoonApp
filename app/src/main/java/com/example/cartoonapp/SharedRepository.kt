package com.example.cartoonapp

import com.example.cartoonapp.domain.mappers.CharacterMapper
import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import com.example.cartoonapp.network.NetworkLayer

class SharedRepository {

    suspend fun getCharatcterById(characterId:Int): com.example.cartoonapp.domain.models.Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return CharacterMapper.buildFrom(response = request.body)

    }

}