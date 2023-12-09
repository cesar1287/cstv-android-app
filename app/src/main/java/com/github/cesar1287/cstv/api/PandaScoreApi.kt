package com.github.cesar1287.cstv.api

import com.github.cesar1287.cstv.model.MatchesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PandaScoreApi {

    @GET("csgo/matches")
    suspend fun getMatches(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
        @Query("sort") sort: String
    ): Response<MatchesResponse>
}