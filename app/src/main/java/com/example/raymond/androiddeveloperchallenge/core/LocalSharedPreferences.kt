package com.example.raymond.androiddeveloperchallenge.core

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.model.PinBoard
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalSharedPreferences {

    private var mInstance: LocalSharedPreferences? = null
    val MY_PREFS_NAME = "raw_sharedpreferences"
    private val KEY_RAW_DATA = "raw_data"
    private val KEY_LIST_RAW_DATA = "lis_raw_data"

    fun getIns(): LocalSharedPreferences? {
        return mInstance
    }

    fun savePinBoard(context: Context, gson: Gson, pinboard: PinBoard) {
        val editor = context.getSharedPreferences(MY_PREFS_NAME, 0).edit()
        editor.putString(KEY_RAW_DATA, gson.toJson(pinboard))
        editor.apply()
    }

    fun getPinBoard(context: Context, gson: Gson): PinBoard {
        val pref = context.getSharedPreferences(MY_PREFS_NAME, 0)
        return gson.fromJson<PinBoard>(pref.getString(KEY_RAW_DATA, ""), PinBoard::class.java!!)
    }

    fun saveListPinBoard(context: Context?, gson: Gson, pinboards: Array<PinBoard>) {
        val editor = context?.getSharedPreferences(MY_PREFS_NAME, 0)?.edit()
        editor?.putString(KEY_LIST_RAW_DATA, gson.toJson(pinboards))
        editor?.apply()
    }

    fun getPinBoards(context: Context?, gson: Gson): Array<PinBoard>? {
        val listType = object : TypeToken<Array<PinBoard>>() {
        }.type
        val pref = context?.getSharedPreferences(MY_PREFS_NAME, 0)
        return gson.fromJson<Array<PinBoard>>(pref?.getString(KEY_LIST_RAW_DATA, ""), listType)
    }

}