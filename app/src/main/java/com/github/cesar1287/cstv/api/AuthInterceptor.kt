package com.github.cesar1287.cstv.api

import com.github.cesar1287.cstv.BuildConfig
import com.github.cesar1287.cstv.utils.Constants.Companion.API_KEY_AUTHORIZATION
import com.github.cesar1287.cstv.utils.Constants.Companion.API_KEY_BEARER
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(API_KEY_AUTHORIZATION, "$API_KEY_BEARER ${BuildConfig.API_TOKEN}")
            .build()
        return chain.proceed(request)
    }
}