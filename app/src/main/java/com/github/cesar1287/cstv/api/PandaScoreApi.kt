package com.github.cesar1287.cstv.api

import retrofit2.http.GET

interface PandaScoreApi {

    @GET("matches")
    suspend fun getMatches()
}