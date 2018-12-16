package com.example.raymond.androiddeveloperchallenge.core.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File
import java.net.URL


class ImageLoader {

    fun loadImageFromUrl(pathImage: String, view: ImageView, context: Context?) {
        context?.let {
            val url = URL(Utils().imageURLFormatter(pathImage))
            val uri = Uri.parse(url.toURI().toString())
            Glide.with(it)
                    .load(uri)
                    .into(view)
        }
    }

    fun loadImageFromFile(context: Context, imageView: ImageView, pathFile: String) {
        val file = File(pathFile)
        Glide.with(context)
                .load(file)
                .into(imageView)
    }

}