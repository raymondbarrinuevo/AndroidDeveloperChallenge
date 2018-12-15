package com.example.raymond.androiddeveloperchallenge.network

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager {
    private var mInstance: ApiManager? = null
    var baseProjectAPI: BaseProjectAPI? = null

    constructor() {
        this.init()
    }

    fun getInstance(): ApiManager? {
        if (mInstance == null) {
            mInstance = ApiManager()
        }
        return mInstance
    }

    private fun init() {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://pastebin.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        this.baseProjectAPI = retrofit.create(BaseProjectAPI::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(createHttpLogging())
                .addInterceptor(HeaderInterceptor())
                .connectTimeout(ApiConstant().CONNECT_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(ApiConstant().CONNECT_TIMEOUT, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build()
    }

    private fun createHttpLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor { message -> Log.d("ufood", message) }
        logging.level = HttpLoggingInterceptor.Level.BASIC

        return logging
    }
}