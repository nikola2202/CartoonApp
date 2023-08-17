package com.example.cartoonapp

import android.icu.text.IDNA.Info
import com.example.cartoonapp.network.data.GetCharacterByIdResponse

data class GetCharactersPageResponse(
    val info: Info = Info(),
    val results: List<GetCharacterByIdResponse> = emptyList()
) {

    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )

}