package com.example.raymond.androiddeveloperchallenge.network

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.core.LocalSharedPreferences
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(val context: Context) : Interceptor {

    var token: String = "";

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        token = LocalSharedPreferences().getToken(context, Gson()).accessToken
        val finalToken = "Bearer $token"

        if (token.isNotEmpty()) {
            request = request.newBuilder()
                .addHeader("Authorization", finalToken)
                .build()
        }

        return chain.proceed(request)
    }
}