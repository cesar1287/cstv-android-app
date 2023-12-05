package com.github.cesar1287.cstv.model

data class Tournament(
    val begin_at: String,
    val detailed_stats: Boolean,
    val end_at: String,
    val has_bracket: Boolean,
    val id: Int,
    val league_id: Int,
    val live_supported: Boolean,
    val modified_at: String,
    val name: String,
    val prizepool: String,
    val serie_id: Int,
    val slug: String,
    val tier: String,
    val winner_id: Int,
    val winner_type: String
)