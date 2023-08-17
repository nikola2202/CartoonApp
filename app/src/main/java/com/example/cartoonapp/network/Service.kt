package com.example.cartoonapp.network

import com.example.cartoonapp.GetCharactersPageResponse
import com.example.cartoonapp.network.data.GetCharacterByIdResponse
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
}