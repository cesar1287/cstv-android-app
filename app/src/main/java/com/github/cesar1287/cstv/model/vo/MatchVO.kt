package com.github.cesar1287.cstv.model.vo

import com.github.cesar1287.cstv.model.MatchStatus

data class MatchVO(
    val id: Int,
    val serieId: Int,
    val nameTeamA: String?,
    val logoTeamA: String?,
    val nameTeamB: String?,
    val logoTeamB: String?,
    val nameLeagueSerie: String?,
    val logoLeague: String?,
    val status: MatchStatus?
)