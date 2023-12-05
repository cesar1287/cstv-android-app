package com.github.cesar1287.cstv.api

import com.github.cesar1287.cstv.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.API_TOKEN}")

            .build()
        return chain.proceed(request)
    }
}