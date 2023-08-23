package com.example.cartoonapp.domain.models

import com.example.cartoonapp.network.response.Location
import com.example.cartoonapp.network.response.Origin

data class Character(
    val episodeList: List<Episode> = listOf(),
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val location: Location = Location(),
    val name: String = "",
    val origin: Origin = Origin(),
    val species: String = "",
    val status: String = ""
) {
    data class Location(
        val name: String = "",
        val url: String = ""
    )
    data class Origin(
        val name: String = "",
        val url: String = ""
    )
}