package com.github.cesar1287.cstv.features.matchdetail.data

import com.github.cesar1287.cstv.api.PandaScoreApi
import com.github.cesar1287.cstv.base.BaseRepository
import com.github.cesar1287.cstv.model.api.TeamsResponse
import com.github.cesar1287.cstv.model.api.toUIModel
import com.github.cesar1287.cstv.model.vo.TeamsVO
import com.github.cesar1287.cstv.utils.ResponseApi
import javax.inject.Inject

interface MatchDetailRepository {

    suspend fun getTeams(teams: String): ResponseApi
}

class MatchDetailRepositoryImpl @Inject constructor(
    private val pandaScoreApi: PandaScoreApi
) : MatchDetailRepository, BaseRepository() {

    override suspend fun getTeams(teams: String): ResponseApi {
        val response = safeApiCall {
            pandaScoreApi.getTeams(teams)
        }
        return when (response) {
            is ResponseApi.Success -> {
                val teamsResponse = response.data as? TeamsResponse
                response.data = teamsResponse?.toUIModel() ?: TeamsVO(
                    teamA = null,
                    teamB = null,
                    noTeamsResponse = true,
                    firstTeamId = 0,
                    secondTeamId = 0
                )

                response
            }

            is ResponseApi.Error -> response
        }
    }
}
