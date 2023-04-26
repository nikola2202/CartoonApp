package com.example.cartoonapp.data

import com.example.cartoonapp.data.Location
import com.example.cartoonapp.data.Origin

data class GetCharacterByIdResponse(
    val created: String = "",
    val episode: List<String> = listOf(),
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val location: Location = Location(),
    val name: String = "",
    val origin: Origin = Origin(),
    val species: String = "",
    val status: String = "",
    val type: String = "",
    val url: String = ""
)