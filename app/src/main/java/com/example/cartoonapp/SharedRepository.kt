package com.example.cartoonapp

import com.example.cartoonapp.domain.mappers.CharacterMapper
import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import com.example.cartoonapp.network.NetworkLayer
import com.example.cartoonapp.network.response.GetEpisodeByIdResponse

class SharedRepository {

    suspend fun getCharatcterById(characterId:Int): com.example.cartoonapp.domain.models.Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)
        return CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
        )

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