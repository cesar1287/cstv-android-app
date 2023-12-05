package com.github.cesar1287.cstv.model.vo

data class MatchVO(
    val id: Int,
    val serieId: Int,
    val nameTeamA: String?,
    val logoTeamA: String?,
    val nameTeamB: String?,
    val logoTeamB: String?,
    val nameLeagueSerie: String?,
    val logoLeague: String?
)