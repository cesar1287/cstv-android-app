package com.github.cesar1287.cstv.features.home.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.cesar1287.cstv.api.PandaScoreApi
import com.github.cesar1287.cstv.model.vo.MatchVO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HomeRepository {

    fun getMatches(): Flow<PagingData<MatchVO>>
}

class HomeRepositoryImpl @Inject constructor(
    private val pandaScoreApi: PandaScoreApi
): HomeRepository {

    override fun getMatches(): Flow<PagingData<MatchVO>> {
        return Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = 20)
        ) {
            HomePagingSource(
                pandaScoreApi = pandaScoreApi
            )
        }.flow
    }
}