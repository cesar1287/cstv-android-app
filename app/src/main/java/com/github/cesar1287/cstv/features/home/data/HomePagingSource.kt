package com.github.cesar1287.cstv.features.home.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.cesar1287.cstv.api.PandaScoreApi
import com.github.cesar1287.cstv.extensions.getRangeApiDate
import com.github.cesar1287.cstv.model.api.MatchStatus
import com.github.cesar1287.cstv.model.api.toUIModel
import com.github.cesar1287.cstv.model.vo.MatchVO
import com.github.cesar1287.cstv.utils.Constants.Companion.API_KEY_QUERY_BEGIN_AT
import com.github.cesar1287.cstv.utils.Constants.Companion.API_KEY_QUERY_STATUS
import retrofit2.HttpException
import java.io.IOException
import java.util.Calendar
import javax.inject.Inject

class HomePagingSource @Inject constructor(
    private val pandaScoreApi: PandaScoreApi
): PagingSource<Int, MatchVO>() {

    private val range = getApiDatesRange()

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, MatchVO> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = pandaScoreApi.getMatches(
                nextPageNumber,
                sort = "$API_KEY_QUERY_STATUS,$API_KEY_QUERY_BEGIN_AT",
                range = "${range.first},${range.second}"
            )

            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body()
                        ?.filter {
                            it.status != MatchStatus.POSTPONED
                        }
                        ?.map {
                            it.toUIModel()
                        } ?: listOf(),
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber + 1
                )
            }
            else {
                LoadResult.Error(UnknownError())
            }
        } catch (e: IOException) {
            // IOException for network failures.
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MatchVO>): Int? {
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

    /*
    * We can use `rangeOfMonths` as a remote config to have a better personalized way to get faster
    * answers from backend, based on user usage, if the overall user never see more than 4/5 pages
    * isn't necessary get a range of matches between 1 year.
    * */
    private fun getApiDatesRange(
        rangeOfMonths: Int? = null
    ): Pair<String, String> {
        val currentDate = Calendar.getInstance()
        val initialDate = Calendar.getInstance()
        initialDate.add(Calendar.MONTH, -(rangeOfMonths ?: 12))

        val firstDate = initialDate.time.getRangeApiDate()
        val secondDate = currentDate.time.getRangeApiDate()
        return Pair(firstDate, secondDate)
    }
}