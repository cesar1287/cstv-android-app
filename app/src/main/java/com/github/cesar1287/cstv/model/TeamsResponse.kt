package com.github.cesar1287.cstv.model

import com.github.cesar1287.cstv.model.vo.TeamsVO

class TeamsResponse : ArrayList<TeamsResponseItem>()

fun TeamsResponse.toUIModel(): TeamsVO {

    return TeamsVO(
        teamA = this.firstOrNull()?.players,
        teamB = this.lastOrNull()?.players,
        noTeamsResponse = this.firstOrNull()?.players?.isEmpty() == true &&
                this.lastOrNull()?.players?.isEmpty() == true,
        firstTeamId = this.firstOrNull()?.id,
        secondTeamId = this.lastOrNull()?.id
    )
}