package com.example.cartoonapp.characters

import com.example.cartoonapp.CharactersDataSource
import com.example.cartoonapp.CharactersRepository
import com.example.cartoonapp.network.data.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope
import javax.sql.DataSource

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
): androidx.paging.DataSource.Factory<Int,GetCharacterByIdResponse>() {
    override fun create(): androidx.paging.DataSource<Int, GetCharacterByIdResponse> {
        return CharactersDataSource(coroutineScope,repository)
    }
}