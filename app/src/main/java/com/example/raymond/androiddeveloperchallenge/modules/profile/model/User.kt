package com.example.raymond.androiddeveloperchallenge.modules.profile.model

import com.example.raymond.androiddeveloperchallenge.core.model.BaseModel

class User(_id: String, _username: String, _name: String, _profileImage: ProfileImage, _links: Links) : BaseModel(_id) {

    var username: String = _username
    var name: String = _name
    var profileImage: ProfileImage = _profileImage
    var links: Links = _links

    class ProfileImage(_small: String, _medium: String, _large: String) {
        companion object {
            fun create(_small: String, _medium: String, _large: String): ProfileImage = ProfileImage(_small, _medium, _large)
        }

        var small: String = _small
        var medium: String = _medium
        var large: String = _large

    }

    class Links(_self: String, _html: String, _photos: String, _likes: String) {
        companion object {
            fun create(_self: String, _html: String, _photos: String, _likes: String):
                    Links = Links(_self, _html, _photos, _likes)
        }

        var self: String = _self
        var html: String = _html
        var photos: String = _photos
        var likes: String = _likes

    }
}