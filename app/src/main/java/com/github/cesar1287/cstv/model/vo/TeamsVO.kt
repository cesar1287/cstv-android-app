package com.github.cesar1287.cstv.model.vo

import android.os.Parcelable
import com.github.cesar1287.cstv.model.api.Player
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamsVO(
    val teamA: List<PlayerVO>?,
    val teamB: List<PlayerVO>?,
    val noTeamsResponse: Boolean,
    val firstTeamId: Int?,
    val secondTeamId: Int?
) : Parcelable