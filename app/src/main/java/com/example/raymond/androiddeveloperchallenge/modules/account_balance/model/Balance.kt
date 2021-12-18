package com.example.raymond.androiddeveloperchallenge.modules.account_balance.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Balance(
    @SerializedName("code")
    @Expose
    val code: Int,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("data")
    @Expose
    val data: Data
)


data class Data(
    val balance: String,
    val currency: String
)