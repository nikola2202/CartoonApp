package com.example.cartoonapp.network

import com.example.cartoonapp.network.response.GetCharactersPageResponse
import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import com.example.cartoonapp.network.response.GetEpisodeByIdResponse
import com.example.cartoonapp.network.response.GetEpisodePageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>

    @GET("character")
    suspend fun getCharactersPage (
        @Query("page") pageIndex: Int
    ): Response<GetCharactersPageResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") episodeId: Int
    ): Response<GetEpisodeByIdResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range") episodeRange: String
    ): Response<List<GetEpisodeByIdResponse>>

    @GET("episode/")
    suspend fun getEpisodePage(
        @Query("page") pageIndex: Int
    ): Response<GetEpisodePageResponse>

}