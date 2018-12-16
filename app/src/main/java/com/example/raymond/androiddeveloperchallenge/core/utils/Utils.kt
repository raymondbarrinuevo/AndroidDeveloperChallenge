package com.example.raymond.androiddeveloperchallenge.core.utils

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    fun imageURLFormatter(url: String): String {
        return url.replace("\\", "")
    }

    fun isInternetOn(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}