package com.example.cartoonapp.episodes

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cartoonapp.domain.mappers.EpisodeMapper
import com.example.cartoonapp.domain.models.Episode
import com.example.cartoonapp.network.NetworkLayer
import kotlinx.coroutines.CoroutineScope

class EpisodePagingSource(
    private val repository: EpisodeRepository
): PagingSource<Int,Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val pageNumber = params.key ?: 1
        val previousKey = if (pageNumber == 1) null else pageNumber - 1

        val pageRequest = NetworkLayer.apiClient.getEpisodePage(pageNumber)

        pageRequest.exception?.let {
            return LoadResult.Error(it)
        }

        return LoadResult.Page(
            data = pageRequest.body.results.map { EpisodeMapper.buildFrom(it) },
            prevKey = null,
            nextKey = getPageIndexFromNext((pageRequest.body.info.next))
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

    private fun getPageIndexFromNext(next: String?):Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

}