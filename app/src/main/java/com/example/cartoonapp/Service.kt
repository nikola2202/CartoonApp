package com.example.cartoonapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("character/{character-id}")
    fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Call<GetCharacterByIdResponse>

}