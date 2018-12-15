package com.example.raymond.androiddeveloperchallenge.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

abstract class BaseModel(@SerializedName("id") val id: String) : Serializable {

}