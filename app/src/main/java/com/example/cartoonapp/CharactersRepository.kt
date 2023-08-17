package com.example.cartoonapp

import com.example.cartoonapp.network.NetworkLayer

class CharactersRepository {

    suspend fun getCharacterPage(pageIndex:Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        return request.body
    }

}