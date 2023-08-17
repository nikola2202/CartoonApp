package com.example.cartoonapp.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.cartoonapp.CharactersRepository
import com.example.cartoonapp.Constants
import com.example.cartoonapp.network.data.GetCharacterByIdResponse

class CharactersViewModel: ViewModel() {

    private val repository = CharactersRepository()
    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = CharactersDataSourceFactory(viewModelScope,repository)
    val charactersPagedListLiveData: LiveData<PagedList<GetCharacterByIdResponse>> = LivePagedListBuilder(
        dataSourceFactory,pageListConfig).build()

}