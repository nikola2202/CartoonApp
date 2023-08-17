package com.example.cartoonapp.characters

import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
): androidx.paging.DataSource.Factory<Int, GetCharacterByIdResponse>() {
    override fun create(): androidx.paging.DataSource<Int, GetCharacterByIdResponse> {
        return CharactersDataSource(coroutineScope,repository)
    }
}