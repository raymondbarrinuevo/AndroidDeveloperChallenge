package com.example.raymond.androiddeveloperchallenge.network

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface BaseProjectAPI {

    @GET("raw/wgkJgazE")
    fun getRaw(): Call<String>
}