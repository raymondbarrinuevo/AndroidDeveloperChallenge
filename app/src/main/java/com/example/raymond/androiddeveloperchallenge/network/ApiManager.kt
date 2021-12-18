package com.example.raymond.androiddeveloperchallenge.network

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.core.LocalSharedPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiManager(val context: Context) {
    private var mInstance: ApiManager? = null
    var baseProjectAPI: BaseProjectAPI? = null

    init {
        this.init()
    }

    fun getInstance(): ApiManager? {
        if (mInstance == null) {
            mInstance = ApiManager(context)
        }
        return mInstance
    }


    private fun init() {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstant().BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.baseProjectAPI = retrofit.create(BaseProjectAPI::class.java)
    }
}