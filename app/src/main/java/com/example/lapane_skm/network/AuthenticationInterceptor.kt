package com.example.lapane_skm.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException


// class ini sabagai header yang digunakan ketika apinya menggunakan basic auth
class AuthenticationInterceptor(private val authToken: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
            .header("Authorization", authToken)

        val request = builder.build()
        return chain.proceed(request)
    }
}