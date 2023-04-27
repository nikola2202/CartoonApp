package com.example.cartoonapp

import com.example.cartoonapp.data.GetCharacterByIdResponse

class SharedRepository {

    suspend fun getCharatcterById(characterId:Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return request.body

    }

}