package com.github.cesar1287.cstv.model

data class Serie(
    val begin_at: String,
    val end_at: String,
    val full_name: String,
    val id: Int,
    val league_id: Int,
    val modified_at: String,
    val name: String,
    val season: String,
    val slug: String,
    val winner_id: Any,
    val winner_type: Any,
    val year: Int
)