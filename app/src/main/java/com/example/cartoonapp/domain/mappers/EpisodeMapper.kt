package com.example.cartoonapp.domain.mappers

import com.example.cartoonapp.domain.models.Episode
import com.example.cartoonapp.network.response.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(networkEpisode: GetEpisodeByIdResponse): Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            episode = networkEpisode.episode
        )
    }

}