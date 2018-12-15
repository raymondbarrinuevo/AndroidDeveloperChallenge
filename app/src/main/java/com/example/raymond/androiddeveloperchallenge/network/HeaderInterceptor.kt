package com.example.raymond.androiddeveloperchallenge.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    private val ACCEPT_HEADER = "Accept"
    private val JSON_TYPE = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader(ACCEPT_HEADER, JSON_TYPE).build()
        return chain.proceed(request)
    }
}