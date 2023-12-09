package com.github.cesar1287.cstv.model.vo

import android.os.Parcelable
import com.github.cesar1287.cstv.model.MatchStatus
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchVO(
    val id: Int,
    val serieId: Int,
    val nameTeamA: String?,
    val logoTeamA: String?,
    val nameTeamB: String?,
    val logoTeamB: String?,
    val nameLeagueSerie: String?,
    val logoLeague: String?,
    val status: MatchStatus?,
    val beginAt: String?
): Parcelable