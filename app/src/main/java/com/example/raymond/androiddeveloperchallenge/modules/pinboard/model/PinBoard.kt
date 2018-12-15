package com.example.raymond.androiddeveloperchallenge.modules.pinboard.model

import com.example.raymond.androiddeveloperchallenge.modules.profile.model.User
import com.google.gson.annotations.SerializedName

class PinBoard(@SerializedName("id") val _id: String,
               @SerializedName("created_at") val _created_at: String,
               @SerializedName("width") val _width: Int,
               @SerializedName("height") val _height: Int,
               @SerializedName("color") val _color: String,
               @SerializedName("likes") val _likes: Int,
               @SerializedName("liked_by_user") val _liked_by_user: String,
               @SerializedName("user") val _user: User,
               @SerializedName("urls") val _urls: Urls,
               @SerializedName("links") val _links: Links,
               @SerializedName("categories") val _categories: Array<Category>) {

    //    var id: String = _id
//    var createdAt: String = _created_at
//    var width: Int = _width
//    var height: Int = _height
//    var color: String = _color
//    var likes: Int = _likes
//    var likedByUser: String = _liked_by_user
//    var user: User = _user
//    var urls: Urls = _urls
    class Urls(_raw: String, _full: String, _regular: String, _small: String, _thumb: String) {
        companion object {
            fun create(_raw: String, _full: String, _regular: String, _small: String, _thumb: String):
                    Urls = Urls(_raw, _full, _regular, _small, _thumb)
        }

        var raw: String = _raw
        var full: String = _full
        var regular: String = _regular
        var small: String = _small
        var thumb: String = _thumb

    }

    class Links(_self: String, _html: String, _download: String) {
        companion object {
            fun create(_self: String, _html: String, _download: String):
                    Links = Links(_self, _html, _download)
        }

        var self: String = _self
        var html: String = _html
        var download: String = _download
    }

    class Category(_id: String, _title: String, _photo_count: String, _links: CategoryLinks) {
        companion object {
            fun create(_id: String, _title: String, _photo_count: String, _links: CategoryLinks):
                    Category = Category(_id, _title, _photo_count, _links)
        }

        var id: String = _id
        var title: String = _title
        var photo_count: String = _photo_count
        var links: CategoryLinks = _links
    }

    class CategoryLinks(_self: String, _photos: String) {
        companion object {
            fun create(_self: String, _photos: String):
                    CategoryLinks = CategoryLinks(_self, _photos)
        }

        var self: String = _self
        var photos: String = _photos
    }
}