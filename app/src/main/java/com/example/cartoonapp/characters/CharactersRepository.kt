package com.example.cartoonapp.characters

import com.example.cartoonapp.network.NetworkLayer
import com.example.cartoonapp.network.response.GetCharactersPageResponse

class CharactersRepository {

    suspend fun getCharacterPage(pageIndex:Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        return request.body
    }

}