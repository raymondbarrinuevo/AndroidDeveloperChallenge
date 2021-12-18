package com.example.raymond.androiddeveloperchallenge.network

import retrofit2.Call
import retrofit2.http.*

interface BaseProjectAPI {

    @GET("balance")
    fun getBalance(): Call<String>


    @POST("login")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    fun login(@Field("email") email: String,
              @Field("password") password: String): Call<String>
}