package com.example.raymond.androiddeveloperchallenge.modules.login.model

import com.example.raymond.androiddeveloperchallenge.core.model.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token_type")
    @Expose
    val tokenType: String,
    @SerializedName("access_token")
    @Expose
    val accessToken: String,
    @SerializedName("expires_in")
    @Expose
    val expiresIn: Int,
    @SerializedName("refresh_token")
    @Expose
    val refreshToken: String = ""
) : BaseModel()

