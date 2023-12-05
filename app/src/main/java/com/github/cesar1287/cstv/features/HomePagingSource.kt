package com.github.cesar1287.cstv.features

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.cesar1287.cstv.api.PandaScoreApi
import com.github.cesar1287.cstv.model.MatchesResponseItem
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomePagingSource @Inject constructor(
    private val pandaScoreApi: PandaScoreApi
): PagingSource<Int, MatchesResponseItem>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, MatchesResponseItem> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = pandaScoreApi.getMatches(nextPageNumber)
            LoadResult.Page(
                data = response.body()?.map { it } ?: listOf(),
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } catch (e: IOException) {
            // IOException for network failures.
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MatchesResponseItem>): Int? {
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