package com.example.raymond.androiddeveloperchallenge.core.utils

class Utils {

    fun imageURLFormatter(url: String): String {
        return url.replace("\\", "")
    }
}