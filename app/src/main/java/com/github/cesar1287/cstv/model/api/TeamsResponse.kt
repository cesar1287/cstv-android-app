package com.github.cesar1287.cstv.model.api

import com.github.cesar1287.cstv.model.vo.TeamsVO

class TeamsResponse : ArrayList<TeamsResponseItem>()

fun TeamsResponse.toUIModel(): TeamsVO {

    return TeamsVO(
        teamA = this.firstOrNull()?.players?.map { it.toUIModel() },
        teamB = this.lastOrNull()?.players?.map { it.toUIModel() },
        noTeamsResponse = this.firstOrNull()?.players?.isEmpty() == true &&
                this.lastOrNull()?.players?.isEmpty() == true,
        firstTeamId = this.firstOrNull()?.id,
        secondTeamId = this.lastOrNull()?.id
    )
}