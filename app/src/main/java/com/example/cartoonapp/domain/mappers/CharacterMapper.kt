package com.example.cartoonapp.domain.mappers

import com.example.cartoonapp.domain.models.Character
import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import com.example.cartoonapp.network.response.GetEpisodeByIdResponse

object CharacterMapper {

    fun buildFrom(
        response: GetCharacterByIdResponse,
        episodes: List<GetEpisodeByIdResponse>
    ): Character {

        return Character(
            episodeList = episodes.map {
                 EpisodeMapper.buildFrom(it)
            },
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status
        )
    }
}