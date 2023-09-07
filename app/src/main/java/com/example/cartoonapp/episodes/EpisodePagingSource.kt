package com.example.cartoonapp.episodes

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cartoonapp.domain.models.Episode
import kotlinx.coroutines.CoroutineScope

class EpisodePagingSource(
    private val coroutineScope: CoroutineScope,
    private val repository: EpisodeRepository
): PagingSource<Int,Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val pageNumber = params.key ?: 1
        val previousKey = if (pageNumber == 1) null else pageNumber - 1

        //todo network call with key

        return LoadResult.Page(
            data = emptyList(),
            prevKey = null,
            nextKey = pageNumber + 1 // todo clean this up with network info
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
}