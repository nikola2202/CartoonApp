package com.example.cartoonapp

import com.example.cartoonapp.domain.mappers.CharacterMapper
import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import com.example.cartoonapp.network.NetworkLayer
import com.example.cartoonapp.network.CartoonAppCache
import com.example.cartoonapp.network.response.GetEpisodeByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId:Int): com.example.cartoonapp.domain.models.Character? {

        //Check the cache for our character
        val cachedCharacter = CartoonAppCache.characterMap[characterId]
        if (cachedCharacter != null) {
            return cachedCharacter
        }

        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)
        val character =  CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
        )

        //Update cache & return value
        CartoonAppCache.characterMap[characterId] = character
        return character
    }

    private suspend fun getEpisodesFromCharacterResponse(
        characterResponse: GetCharacterByIdResponse
    ): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body

    }

}