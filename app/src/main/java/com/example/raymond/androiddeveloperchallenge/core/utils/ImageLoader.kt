package com.example.raymond.androiddeveloperchallenge.core.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


class ImageLoader {

    fun loadImage(url: String, view: ImageView, context: Context?): Boolean {
        var isLoaded = false
        context?.let {
            Glide.with(it)
                    .load(Utils().imageURLFormatter(url))
                    .into(view)
        }
        return isLoaded
    }

}