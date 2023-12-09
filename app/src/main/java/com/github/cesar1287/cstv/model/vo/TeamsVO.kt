package com.github.cesar1287.cstv.model.vo

import android.os.Parcelable
import com.github.cesar1287.cstv.model.Player
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamsVO(
    val teamA: List<Player>?,
    val teamB: List<Player>?,
    val noTeamsResponse: Boolean,
    val firstTeamId: Int?,
    val secondTeamId: Int?
) : Parcelable