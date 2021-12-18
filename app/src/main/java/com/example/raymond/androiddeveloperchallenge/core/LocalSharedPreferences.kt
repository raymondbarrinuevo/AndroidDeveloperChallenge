package com.example.raymond.androiddeveloperchallenge.core

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.modules.login.model.Token
import com.google.gson.Gson

class LocalSharedPreferences {

    private var mInstance: LocalSharedPreferences? = null
    val MY_PREFS_NAME = "rawSharedPreferences"
    private val TOKEN = "token"

    fun getIns(): LocalSharedPreferences? {
        return mInstance
    }

    fun saveToken(context: Context, gson: Gson, token: Token) {
        val editor = context.getSharedPreferences(MY_PREFS_NAME, 0).edit()
        editor.putString(TOKEN, gson.toJson(token))
        editor.apply()
    }

    fun getToken(context: Context, gson: Gson): Token {
        val pref = context.getSharedPreferences(MY_PREFS_NAME, 0)
        return gson.fromJson<Token>(pref.getString(TOKEN, ""), Token::class.java!!)
    }

}