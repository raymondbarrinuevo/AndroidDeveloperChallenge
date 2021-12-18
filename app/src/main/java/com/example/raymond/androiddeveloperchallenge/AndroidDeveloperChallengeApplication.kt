package com.example.raymond.androiddeveloperchallenge

import android.app.Application
import android.content.Context
import android.os.StrictMode
import androidx.multidex.MultiDex
import com.google.gson.Gson

class AndroidDeveloperChallengeApplication : Application() {

    private var gson: Gson? = null

    private var context: Context? = null
    private var mInstance: AndroidDeveloperChallengeApplication? = null

    fun getInstance(): AndroidDeveloperChallengeApplication? {
        if (mInstance == null) {
            mInstance = AndroidDeveloperChallengeApplication()
        }
        return mInstance
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        gson = Gson()

        context = applicationContext
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

    fun getGson(): Gson? {
        return gson
    }

    fun getContext(): Context? {
        return context
    }

    fun objectToJson(obj: Any): String? {
        return gson?.toJson(obj)
    }
}