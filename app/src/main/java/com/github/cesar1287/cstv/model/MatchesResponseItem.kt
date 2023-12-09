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
    val serie: Serie,
    @SerializedName("serie_id")
    val serieId: Int,
    val status: MatchStatus,
    @SerializedName("begin_at")
    val beginAt: String,
) : Parcelable

fun MatchesResponseItem.toUIModel(): MatchVO {
    val teamA = this.opponents.firstOrNull()?.opponent
    val teamB = this.opponents.lastOrNull()?.opponent

    return MatchVO(
        id = this.id,
        serieId = this.serieId,
        teamAId = teamA?.id ?: 0,
        nameTeamA = teamA?.name,
        logoTeamA = teamA?.imageUrl ?: "",
        teamBId = teamB?.id ?: 0,
        nameTeamB = teamB?.name,
        logoTeamB = teamB?.imageUrl ?: "",
        nameLeagueSerie = "${league.name} - ${serie.fullName}",
        logoLeague = league.imageUrl,
        status = this.status,
        beginAt = this.beginAt
    )
}

enum class MatchStatus {
    @SerializedName("finished")
    FINISHED,

    @SerializedName("not_started")
    NOT_STARTED,

    @SerializedName("running")
    RUNNING,

    @SerializedName("not_played")
    STATUS_NOT_PLAYED,

    @SerializedName("postponed")
    POSTPONED
}