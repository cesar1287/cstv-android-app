package com.github.cesar1287.cstv.model

import android.os.Parcelable
import com.github.cesar1287.cstv.model.vo.MatchVO
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchesResponseItem(
    val id: Int,
    val league: League,
    @SerializedName("league_id")
    val leagueId: Int,
    val name: String,
    val opponents: List<Opponent>,
    @SerializedName("original_scheduled_at")
    val originalScheduledAt: String,
    val serie: Serie,
    @SerializedName("serie_id")
    val serieId: Int,
    val status: String
) : Parcelable

fun MatchesResponseItem.toUIModel(): MatchVO {
    val teamA = this.opponents.firstOrNull()?.opponent
    val teamB = this.opponents.lastOrNull()?.opponent

    return MatchVO(
        id = this.id,
        serieId = this.serieId,
        nameTeamA = teamA?.name ?: "",
        logoTeamA = teamA?.imageUrl ?: "",
        nameTeamB = teamB?.name ?: "",
        logoTeamB = teamB?.imageUrl ?: "",
        nameLeagueSerie = "${league.name} - ${serie.fullName}"
    )
}