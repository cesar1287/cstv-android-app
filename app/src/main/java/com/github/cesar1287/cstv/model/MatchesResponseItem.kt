package com.github.cesar1287.cstv.model

data class MatchesResponseItem(
    val detailed_stats: Boolean,
    val draw: Boolean,
    val forfeit: Boolean,
    val game_advantage: Any,
    val id: Int,
    val league: League,
    val league_id: Int,
    val match_type: String,
    val modified_at: String,
    val name: String,
    val number_of_games: Int,
    val opponents: List<Opponent>,
    val original_scheduled_at: String,
    val rescheduled: Boolean,
    val serie: Serie,
    val serie_id: Int,
    val slug: String,
    val status: String,
    val tournament: Tournament,
    val tournament_id: Int,
    val winner: WinnerX,
    val winner_id: Int,
    val winner_type: String
)