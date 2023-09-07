package com.example.cartoonapp.episodes

import com.example.cartoonapp.network.NetworkLayer
import com.example.cartoonapp.network.response.GetEpisodePageResponse

class EpisodeRepository {

    suspend fun fetchPageIndex(pageIndex: Int): GetEpisodePageResponse? {
        val pageRequest = NetworkLayer.apiClient.getEpisodePage(pageIndex)
        if (!pageRequest.isSuccessful) {
            return null
        }
        return pageRequest.body
    }

}