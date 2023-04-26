package com.example.cartoonapp

import com.example.cartoonapp.data.GetCharacterByIdResponse
import retrofit2.Response

class ApiClient(
    private val cartoonAppService: Service
) {

    suspend fun getCharatcterById(characterId:Int): Response<GetCharacterByIdResponse> {
        return cartoonAppService.getCharacterById(characterId)
    }

}