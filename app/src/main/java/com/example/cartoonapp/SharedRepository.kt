package com.example.cartoonapp

import com.example.cartoonapp.data.GetCharacterByIdResponse

class SharedRepository {

    suspend fun getCharatcterById(characterId:Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharatcterById(characterId)

        if (request.isSuccessful) {
            return request.body()!!
        }

        return null

    }

}